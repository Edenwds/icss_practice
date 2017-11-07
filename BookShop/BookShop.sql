create table BUser  (
   uname                varchar2(30)                    not null,
   pwd                  varchar2(18)                    not null,
   role                 number(1)                       not null,
   constraint PK_BUSER primary key (uname)
);


create table TBook  (
   isbn                 varchar2(18)                    not null,
   bname                varchar2(80)                    not null,
   author               varchar2(100),
   pubdate              date,
   press                varchar2(120),
   price                number(5,1)                     not null,
   descr                varchar2(4000),
   pic                  blob,
   constraint PK_TBOOK primary key (isbn)
);



create table TOrder  (
   oid                  varchar2(35)                    not null,
   uname                varchar2(30),
   allMoney             number(6,1),
   paytime              date,
   constraint PK_TORDER primary key (oid)
);

alter table TOrder
   add constraint FK_TORDER_REFERENCE_BUSER foreign key (uname)
      references BUser (uname);




create table TOrderDetail  (
   aid                  number(9)                       not null,
   oid                  varchar2(35),
   isbn                 varchar2(18),
   buycount             number(5),
   dealPrice            number(5,1),
   constraint PK_TORDERDETAIL primary key (aid)
);

alter table TOrderDetail
   add constraint FK_TORDERDE_REFERENCE_TORDER foreign key (oid)
      references TOrder (oid);

alter table TOrderDetail
   add constraint FK_TORDERDE_REFERENCE_TBOOK foreign key (isbn)
      references TBook (isbn);