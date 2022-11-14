create table if not exists demo
(
    pk_column       bigint auto_increment
        primary key,
    int_column      int            null,
    date_column     date           null,
    datetime_column datetime       null,
    char_column     char           null,
    varchar_column  varchar(60)    null,
    decimal_column  decimal(10, 4) null
)
    charset = utf8mb4;