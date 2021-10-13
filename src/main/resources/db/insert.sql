set foreign_key_checks=0;

truncate table product;
truncate table feedback;

insert into product(`id`, `name`, `price`, `details`, `currency`)
values(110, 'luxury chair', 4500, 'Quisque velit nisi, pretium ut lacinia in', 'FRC'),
(111, 'luxury sofa', 4000, 'Quisque velit nisi, pretium ut lacinia in', 'USD'),
(112, 'luxury bench', 6500, 'Quisque velit nisi, pretium ut lacinia in', 'NGN'),
(113, 'luxury table', 8500, 'Quisque velit nisi, pretium ut lacinia in', 'NGN');

set foreign_key_checks=1;