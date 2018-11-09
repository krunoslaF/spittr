package data;

public interface SpitterRepository {

	Spitter findSpitter(Long id);
	Spitter save(Spitter spitter);
	Spitter findByUsername(String username);

}
