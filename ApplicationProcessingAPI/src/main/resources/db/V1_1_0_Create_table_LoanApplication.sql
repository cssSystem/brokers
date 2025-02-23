CREATE TABLE IF NOT EXISTS loan_application (
    id bigserial primary key,
    loan_amount int not null,
    loan_term int not null,
    users_income int not null,
    credit_load int not null,
    credit_rating int not null,
    status varchar(50) not null default 'PROCESSING',
    timeset timestamp not null default CURRENT_TIMESTAMP
);
