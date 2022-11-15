create table ADMIN (
    adminid varchar(80) not null,
    constraint pk_admin primary key (adminid),
    constraint fk_account foreign key (adminid)
    references ACCOUNT (userid)
);