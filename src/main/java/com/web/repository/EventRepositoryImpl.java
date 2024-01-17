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
import com.web.domain.QCharactor;
import com.web.domain.QEventCharactor;
import com.web.domain.QGroup;

public class EventRepositoryImpl extends QuerydslRepositorySupport implements EventRepositoryCustom {
	
	@Autowired
    private JPAQueryFactory queryFactory;

	public EventRepositoryImpl() {
		super(CafeEvent.class);
	}
	
	QCafeEvent event = QCafeEvent.cafeEvent;
	QEventCharactor eChar = QEventCharactor.eventCharactor;
	QCharactor charactor = QCharactor.charactor;
	QGroup group = QGroup.group;

	@Override
	public Page<CafeEvent> findBySearchOption(Pageable pageable, String groupName, String eventCharactor,
			Date startDate, Date endDate) {
		JPQLQuery<CafeEvent> query = queryFactory.selectFrom(event)
				.leftJoin(event.eventCharacterList, eChar)
				.leftJoin(eChar.charactor, charactor)
				.leftJoin(charactor.group, group)
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
        return charactor.charactorName.containsIgnoreCase(eventCharactor);
    }

    private BooleanExpression containGroupName(String groupName) {
        if (groupName == null || groupName.isEmpty()) {
            return null;
        }
        return group.groupName.containsIgnoreCase(groupName);
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
