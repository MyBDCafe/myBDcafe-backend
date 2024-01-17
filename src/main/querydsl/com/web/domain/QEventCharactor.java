package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventCharactor is a Querydsl query type for EventCharactor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventCharactor extends EntityPathBase<EventCharactor> {

    private static final long serialVersionUID = 2100241448L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventCharactor eventCharactor = new QEventCharactor("eventCharactor");

    public final QCafeEvent cafeEvent;

    public final QCharactor charactor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEventCharactor(String variable) {
        this(EventCharactor.class, forVariable(variable), INITS);
    }

    public QEventCharactor(Path<? extends EventCharactor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventCharactor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventCharactor(PathMetadata metadata, PathInits inits) {
        this(EventCharactor.class, metadata, inits);
    }

    public QEventCharactor(Class<? extends EventCharactor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cafeEvent = inits.isInitialized("cafeEvent") ? new QCafeEvent(forProperty("cafeEvent")) : null;
        this.charactor = inits.isInitialized("charactor") ? new QCharactor(forProperty("charactor"), inits.get("charactor")) : null;
    }

}

