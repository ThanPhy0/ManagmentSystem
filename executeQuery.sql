
--join room_id and orders, menu
select room.room, menu.name, orders.quantity, menu.price from room join orders on room.id = orders.room join menu on orders.menu = menu.id where room.room = 1;

--join girls and room_id
select room.room, girls.name from room join invite_girls on room.id = invite_girls.room join girls on invite_girls.girls_id = girls.id where room.room = 1;


select * from girls;