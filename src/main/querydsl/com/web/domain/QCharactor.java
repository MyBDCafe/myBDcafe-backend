package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharactor is a Querydsl query type for Charactor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCharactor extends EntityPathBase<Charactor> {

    private static final long serialVersionUID = 864932156L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCharactor charactor = new QCharactor("charactor");

    public final StringPath charactorName = createString("charactorName");

    public final QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCharactor(String variable) {
        this(Charactor.class, forVariable(variable), INITS);
    }

    public QCharactor(Path<? extends Charactor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCharactor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCharactor(PathMetadata metadata, PathInits inits) {
        this(Charactor.class, metadata, inits);
    }

    public QCharactor(Class<? extends Charactor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
    }

}

