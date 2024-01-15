package com.web.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.domain.CafeEvent;
import com.web.domain.QCafeEvent;

public class EventRepositoryImpl extends QuerydslRepositorySupport implements EventRepositoryCustom {
	
	@Autowired
    private JPAQueryFactory queryFactory;

	public EventRepositoryImpl() {
		super(CafeEvent.class);
	}
	
	QCafeEvent event = QCafeEvent.cafeEvent;

	@Override
	public Page<CafeEvent> findBySearchOption(Pageable pageable, String groupName, String eventCharactor,
			Date startDate, Date endDate) {
		JPQLQuery<CafeEvent> query = queryFactory.selectFrom(event)
				.where(containGroupName(groupName), containEventCharactor(eventCharactor), 
						containStartDate(startDate), containEndDate(endDate));
		
		JPQLQuery<CafeEvent> appliedQuery = this.getQuerydsl().applyPagination(pageable, query);

	    List<CafeEvent> cafeEvents = appliedQuery.fetch();
	    long total = appliedQuery.fetchCount();

	    return new PageImpl<CafeEvent>(cafeEvents, pageable, total);
	}
	
	private BooleanExpression containEventCharactor(String eventCharactor) {
        if (eventCharactor == null || eventCharactor.isEmpty()) {
            return null;
        }
        return event.eventCharactor.containsIgnoreCase(eventCharactor);
    }

    private BooleanExpression containGroupName(String groupName) {
        if (groupName == null || groupName.isEmpty()) {
            return null;
        }
        return event.groupName.containsIgnoreCase(groupName);
    }

    private BooleanExpression containStartDate(Date startDate) {
        if (startDate == null) {
            return null;
        }
        return event.startDate.goe(startDate); // 이벤트 시작일이 startDate 이후인 경우
    }

    private BooleanExpression containEndDate(Date endDate) {
        if (endDate == null) {
            return null;
        }
        return event.endDate.loe(endDate); // 이벤트 종료일이 endDate 이전인 경우
    }
	
	

}
