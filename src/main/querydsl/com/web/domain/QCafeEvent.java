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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCafeEvent cafeEvent = new QCafeEvent("cafeEvent");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<BusinessHours, QBusinessHours> businessHours = this.<BusinessHours, QBusinessHours>createList("businessHours", BusinessHours.class, QBusinessHours.class, PathInits.DIRECT2);

    public final QCharactor charactor;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final DatePath<java.util.Date> endDate = createDate("endDate", java.util.Date.class);

    public final StringPath eventName = createString("eventName");

    public final StringPath eventUrl = createString("eventUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLocation location;

    public final StringPath memo = createString("memo");

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final DatePath<java.util.Date> startDate = createDate("startDate", java.util.Date.class);

    public QCafeEvent(String variable) {
        this(CafeEvent.class, forVariable(variable), INITS);
    }

    public QCafeEvent(Path<? extends CafeEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCafeEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCafeEvent(PathMetadata metadata, PathInits inits) {
        this(CafeEvent.class, metadata, inits);
    }

    public QCafeEvent(Class<? extends CafeEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactor = inits.isInitialized("charactor") ? new QCharactor(forProperty("charactor"), inits.get("charactor")) : null;
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location"), inits.get("location")) : null;
    }

}

