select p.id, p.name, c.name from person as p
join company as c on p.company_id = c.id
where p.company_id != 5;

select c.name, count(p.company_id) from company as c
join person as p on c.id = p.company_id
group by c.name
having count(p.company_id) = (
	select count(company_id) as c from person
	group by company_id
	order by c DESC
	limit 1	
);