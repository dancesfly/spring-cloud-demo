package cn.demo.service0.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cn.demo.model.Person;

@Service
public class PersonService {
	
	@Autowired
	private MongoTemplate oprations;
	
	public Person insert(Person person){
		oprations.insert(person);
		return person;
	}
	
	public Person findById(String id){
		//select * from persion where id = {id}
		//System.out.println("findById "+id);
		//System.out.println(oprations.findOne(new Query(Criteria.where("id").is(id)), Person.class));
		System.out.println(oprations);
		return oprations.findOne(new Query(Criteria.where("id").is(id)), Person.class);
		//return oprations.findOne(Person.class);
	}
	
	public List<Person> findByName(String name){
		
		Criteria cri=new Criteria();
		
		//or
		cri.orOperator(Criteria.where("name").is("简历"),Criteria.where("name").ne("vivi"));
		
		//and
		cri.and("name").is("vivi").and("id").is("abc");
		
		//se
		Aggregation aggration = Aggregation.newAggregation(
				group("name").sum("id").as("sumId"), //group by id
				match(Criteria.where("id").in("a","b")), //where
				project("sumId").and("name").previousOperation() //select sum("id") as sumid, name
		);
		
		Query query=new Query();
		Criteria criteria=new Criteria();
		criteria.orOperator(Criteria.where("name").is("简历"),Criteria.where("name").ne("vivi"));
		query.addCriteria(criteria);
		return oprations.find(new Query(cri), Person.class);
	}	
}
