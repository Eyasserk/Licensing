package es.etsit.silcam.repository.gis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import es.etsit.silcam.entity.gis.Parcela;

@Repository("parcelaRepository")
public class ParcelaRepositoryImpl implements ParcelaRepository{

	private final MongoOperations mongoOperations;
	
	@SuppressWarnings("deprecation")
	@Autowired
	public ParcelaRepositoryImpl(MongoOperations mongoOperations) {
		Assert.notNull(mongoOperations);
		this.mongoOperations = mongoOperations;
	}
	
	@Override
	public List<Parcela> findAll(){
		return mongoOperations.findAll(Parcela.class);
	}
	
	@Override
	public Parcela findById(String id){
		Query query = new Query(Criteria.where("id").is(id));
		return mongoOperations.findOne(query, Parcela.class);
	}
	
	@Override
	public List<Parcela> findByIdExpediente(long idExpediente){
		Query query = new Query(Criteria.where("expedienteId").is(idExpediente));
		return mongoOperations.find(query, Parcela.class);
	}
	
	@Override
	public Parcela create(Parcela parcela) {
		mongoOperations.save(parcela);
		return parcela;
	}
	
	@Override
	public Parcela update(Parcela parcela) {
		mongoOperations.save(parcela);
		return parcela;
	}
}
