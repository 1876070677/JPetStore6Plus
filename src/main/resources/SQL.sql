create table ADMIN (
    adminid varchar(80) not null,
    constraint pk_admin primary key (adminid),
    constraint fk_account foreign key (adminid)
    references ACCOUNT (userid)
);

create table DIARY (
    no int AUTO_INCREMENT PRIMARY KEY,
    imgurl varchar(255) default "",
    userid varchar(80) not null,
    categoryid varchar(80) not null,
    date datetime not null default CURRENT_TIMESTAMP,
    title varchar(80) not null,
    content text default "",
    views int default 0,
    constraint fk_diary_account foreign key (userid)
    references ACCOUNT (userid) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint fk_diary_category foreign key (categoryid)
    references CATEGORY (catid) ON UPDATE CASCADE ON DELETE CASCADE
);

create table COMMENTS (
    c_no int AUTO_INCREMENT PRIMARY KEY,
    d_no int not null,
    userid varchar(80) not null,
    date datetime not null default CURRENT_TIMESTAMP,
    comment varchar(1000) not null,
    constraint fk_comments_account foreign key (userid)
    references ACCOUNT (userid) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint fk_comments_diary foreign key (d_no)
    references DIARY (no) ON UPDATE CASCADE ON DELETE CASCADE
);

create table LIKES (
    d_no int not null,
    userid varchar(80) not null,
    constraint fk_likes_account foreign key (userid)
    references ACCOUNT (userid) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint fk_likes_diary foreign key (d_no)
    references DIARY (no) ON UPDATE CASCADE ON DELETE CASCADE
);