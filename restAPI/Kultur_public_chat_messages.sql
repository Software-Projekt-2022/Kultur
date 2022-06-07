create view chat_messages(chat_id, name, message_id, content, date, chat) as
SELECT chat.chat_id,
       chat.name,
       m.message_id,
       m.content,
       m.date,
       m.chat
FROM chat
         JOIN message m ON m.chat = chat.chat_id;

alter table chat_messages
    owner to postgres;

