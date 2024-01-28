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
import com.web.domain.QGroup;

public class EventRepositoryImpl extends QuerydslRepositorySupport implements EventRepositoryCustom {
	
	@Autowired
    private JPAQueryFactory queryFactory;

	public EventRepositoryImpl() {
		super(CafeEvent.class);
	}
	
	QCafeEvent event = QCafeEvent.cafeEvent;
	QCharactor charactor = QCharactor.charactor;
	QGroup group = QGroup.group;

	@Override
	public Page<CafeEvent> findEvent(Pageable pageable, String groupName, String charactorName, Date startDate, Date endDate) {
		JPQLQuery<CafeEvent> query = queryFactory.selectFrom(event)
				.leftJoin(event.charactor, charactor)
				.leftJoin(charactor.group, group)
				.where(containGroupName(groupName), containEventCharactor(charactorName), betweenDate(startDate, endDate));
		
		JPQLQuery<CafeEvent> appliedQuery = this.getQuerydsl().applyPagination(pageable, query);
		
	    List<CafeEvent> cafeEvents = appliedQuery.fetch();
	    long total = appliedQuery.fetchCount();
	    return new PageImpl<CafeEvent>(cafeEvents, pageable, total);
	}
	
	private BooleanExpression containEventCharactor(String charactorName) {
        if (charactorName == null || charactorName.isEmpty()) {
            return null;
        }
        return charactor.charactorName.containsIgnoreCase(charactorName);
    }

    private BooleanExpression containGroupName(String groupName) {
        if (groupName == null || groupName.isEmpty()) {
            return null;
        }
        return group.groupName.containsIgnoreCase(groupName);
    }
    
    private BooleanExpression betweenDate(Date startDate, Date endDate) {
    	if (startDate == null && endDate == null) {
    		return null;
        }
    	System.out.println(event.startDate.toString());
    	return event.startDate.between(startDate, endDate).or(event.endDate.between(startDate, endDate));
    }
	
	

}
