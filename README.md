#emailProperties 

spring.application.name=email

server.port=8082

spring.datasource.url= jdbc:postgresql://localhost:5432/ms-email
spring.datasource.username=*****
spring.datasource.password=******
spring.jpa.hibernate.ddl-auto=update

spring.rabbitmq.addresses=amqps://bvtaznku:Bc6VHWagFl1O2UTSlHGlL-LKDGcefNiU@leopard.lmq.cloudamqp.com/*****

broker.queue.email.name=default.email

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=contato.***@gmail.com
spring.mail.password=**** **** **** ****
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

#userProperties

spring.application.name=user

server.port=8081

spring.datasource.url= jdbc:postgresql://localhost:5432/ms-user
spring.datasource.username=*****
spring.datasource.password=*****
spring.jpa.hibernate.ddl-auto=update


spring.rabbitmq.addresses=amqps://bvtaznku:Bc6VHWagFl1O2UTSlHGlL-LKDGcefNiU@leopard.lmq.cloudamqp.com/*****

broker.queue.email.name=default.email
