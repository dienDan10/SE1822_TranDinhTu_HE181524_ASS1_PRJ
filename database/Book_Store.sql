create table [admin] (
	id int identity(1,1) primary key,
	email varchar(50) not null,
	password varchar(50) not null
)

insert into admin (email, password) 
values ('admin@gmail.com', 'admin')

create table users (
	id int identity(1,1) primary key,
	[name] varchar(50) not null,
	email varchar(50) not null,
	password varchar(50) not null
)

insert into users (name, email, password) 
values ('Test User', 'user@gmail.com', 'user')

create table book (
	id int identity(1,1) primary key,
	[name] varchar(100) not null,
	description varchar(100),
	author varchar(50) not null,
	published varchar(50) not null,
	ISBN varchar(50) not null,
	[length] varchar(50),
	price decimal(20,2) not null,
	summary varchar(max),
	[image] varchar(100),
)

create table cart (
	id int identity(1,1) primary key,
	[user_id] int not null references users(id) 
		on delete cascade
		on update cascade,
	book_id int not null references book(id)
		on delete cascade
		on update cascade,
	quantity int not null
)


create table address (
	id int identity(1,1) primary key,
	user_id int references users(id)
	on delete cascade
	on update cascade,
	receiver_name nvarchar(50) not null,
	phone varchar(50) not null,
	village nvarchar(50) not null,
	district nvarchar(50) not null,
	province nvarchar(50) not null,
	detail nvarchar(500)
)


create table orders (
	id int identity(1,1) primary key,
	user_id int references users(id)
		on delete cascade
		on update cascade,
	book_id int references book(id)
		on delete cascade
		on update cascade not null,
	quantity int not null,
	order_date datetime not null,
	[address] nvarchar(500) not null,
	price decimal(20,2) not null,
	payment_method varchar(50) not null,
	[status] varchar(50) not null
)


insert into book(name, description, author, published, ISBN, length, price, summary, image)
values ('Harry Potter', '', 'skdfj', 'adf', '3413', '524', '454', 'fasdfa', 'afsdf')

insert into book(name, description, author, published, ISBN, length, price, summary, image)
values('Brave New World', 'Special 3D Edition', 'Aldous Huxley', '06/11/2014', '9781784870140', '288', '9.99',
'Far in the future, the World Controllers have created the ideal society. Through clever use of genetic engineering, brainwashing and recreational sex and drugs all its members are happy consumers. Bernard Marx seems alone harbouring an ill-defined longing to break free. A visit to one of the few remaining Savage Reservations where the old, imperfect life still continues, may be the cure for his distress...

Huxley''s ingenious fantasy of the future sheds a blazing light on the present and is considered to be his most enduring masterpiece.',
'brave-new-world.jpg')

insert into book(name, author, published, ISBN, length, price, summary, image)
values('Wide Sargasso Sea', 'Jean Rhys', '06/10/2016', '9780241281901', '192', '16.99',
'Born into the oppressive, colonialist society of 1930s Jamaica, white Creole heiress Antoinette Cosway meets a young Englishman who is drawn to her innocent beauty and sensuality. After their marriage, however, disturbing rumours begin to circulate which poison her husband against her. Caught between his demands and her own precarious sense of belonging, Antoinette is inexorably driven towards madness, and her husband into the arms of another novel''s heroine. This classic study of betrayal, a seminal work of postcolonial literature, is Jean Rhys''s brief, beautiful masterpiece.',
'wide-sargasso-see.jpg')

insert into book(name, author, published, ISBN, length, price, summary, image)
values ('In Cold Blood', 'Truman Capote', '03/02/2000', '9780141182575', '320', '9.99',
'The chilling true crime ''non-fiction novel'' that made Truman Capote''s name, In Cold Blood is a seminal work of modern prose, a remarkable synthesis of journalistic skill and powerfully evocative narrative published in Penguin Modern Classics.

Controversial and compelling, In Cold Blood reconstructs the murder in 1959 of a Kansas farmer, his wife and both their children. Truman Capote''s comprehensive study of the killings and subsequent investigation explores the circumstances surrounding this terrible crime and the effect it had on those involved. At the centre of his study are the amoral young killers Perry Smith and Dick Hickcock, who, vividly drawn by Capote, are shown to be reprehensible yet entirely and frighteningly human.

Truman Capote (1924-84) was born in New Orleans. He left school when he was fifteen and subsequently worked for The New Yorker, which provided his first - and last - regular job. He wrote both fiction and non-fiction - short stories, novels and novellas, travel writing, profiles, reportage, memoirs, plays and films; his other works include In Cold Blood (1965), Music for Chameleons (1980) and Answered Prayers (1986), all of which are published in Penguin Modern Classics.',
'in-cold-blood.jpg')

insert into book(name, author, published, ISBN, length, price, summary, image)
values ('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', '05/03/2020', '9780241968581',
'432', '9.99', 'Gabriel García Márquez''s great masterpiece is the story of seven generations of the Buendía family and of Macondo, the town they built. Though little more than a settlement surrounded by mountains, Macondo has its wars and disasters, even its wonders and its miracles. A microcosm of Columbian life, its secrets lie hidden, encoded in a book, and only Aureliano Buendía can fathom its mysteries and reveal its shrouded destiny.

Blending political reality with magic realism, fantasy and comic invention, One Hundred Years of Solitude is one of the most daringly original works of the twentieth century.', 
'one-hundred-year-of-solitude.jpg')

insert into book(name, author, published, ISBN, length, price, summary, image)
values ('To Kill A Mockingbird','Harper Lee', '04/06/2015','9781784752637', 
'320', '9.99', 'A lawyer''s advice to his children as he defends the real mockingbird of Harper Lee''s classic novel - a black man falsely charged with the rape of a white girl. Through the young eyes of Scout and Jem Finch, Harper Lee explores with exuberant humour the irrationality of adult attitudes to race and class in the Deep South of the 1930s. The conscience of a town steeped in prejudice, violence and hypocrisy is pricked by the stamina of one man''s struggle for justice. But the weight of history will only tolerate so much.

To Kill a Mockingbird is a coming-of-age story, an anti-racist novel, a historical drama of the Gre', 
'to-kill-a-mockingbird.jpg')

insert into book(name, author, published, ISBN, length, price, summary, image)
values ('Pride and Prejudice', 'Jane Austen', '06/12/2012', '9780141199078', '416', '7.99', 
'When Elizabeth Bennet first meets eligible bachelor Fitzwilliam Darcy, she thinks him arrogant and conceited; he is indifferent to her good looks and lively mind. When she later discovers that Darcy has involved himself in the troubled relationship between his friend Bingley and her beloved sister Jane, she is determined to dislike him more than ever. In the sparkling comedy of manners that follows, Jane Austen shows the folly of judging by first impressions and superbly evokes the friendships, gossip and snobberies of provincial middle-class life',
'pride-and-prejudice.jpg')

insert into book(name, author, published, ISBN, length, price, summary, image)
values ('The Great Gatsby', 'F. Scott Fitzgerald', '07/06/2018','9780241341469','160',
'7.99', 'Jay Gatsby is the man who has everything. But one thing will always be out of his reach ... Everybody who is anybody is seen at his glittering parties. Day and night his Long Island mansion buzzes with bright young things drinking, dancing and debating his mysterious character. For Gatsby - young, handsome, fabulously rich - always seems alone in the crowd, watching and waiting, though no one knows what for. Beneath the shimmering surface of his life he is hiding a secret: a silent longing that can never be fulfilled. And soon this destructive obsession will force his world to unravel.', 
'the-great-gatsby.jpg')