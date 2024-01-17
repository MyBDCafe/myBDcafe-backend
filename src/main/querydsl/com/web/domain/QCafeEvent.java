package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCafeEvent is a Querydsl query type for CafeEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCafeEvent extends EntityPathBase<CafeEvent> {

    private static final long serialVersionUID = 1295226842L;

    public static final QCafeEvent cafeEvent = new QCafeEvent("cafeEvent");

    public final TimePath<java.util.Date> closeTime = createTime("closeTime", java.util.Date.class);

    public final DatePath<java.util.Date> endDate = createDate("endDate", java.util.Date.class);

    public final ListPath<EventCharactor, QEventCharactor> eventCharacters = this.<EventCharactor, QEventCharactor>createList("eventCharacters", EventCharactor.class, QEventCharactor.class, PathInits.DIRECT2);

    public final StringPath eventLocation = createString("eventLocation");

    public final StringPath eventName = createString("eventName");

    public final StringPath eventUrl = createString("eventUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final TimePath<java.util.Date> openTime = createTime("openTime", java.util.Date.class);

    public final DatePath<java.util.Date> startDate = createDate("startDate", java.util.Date.class);

    public QCafeEvent(String variable) {
        super(CafeEvent.class, forVariable(variable));
    }

    public QCafeEvent(Path<? extends CafeEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCafeEvent(PathMetadata metadata) {
        super(CafeEvent.class, metadata);
    }

}

