package data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {
	
	private List<Spitter> spitterList = new ArrayList<>();

	@Override
	public Spitter findSpitter(Long id) {
		for (Spitter item : spitterList) {
			if (item.getId() == id) {
				return item;
			}
		}		
		return null;
	}

	@Override
	public Spitter save(Spitter spitter) {
		spitterList.add(spitter);
		return spitter;
	}

	@Override
	public Spitter findByUsername(String username) {
		for (Spitter item : spitterList) {
			if (item.getUsername().equals(username)) {
				return item;
			}
		}
		return null;
	}

}
