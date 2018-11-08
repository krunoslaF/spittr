package data;

import java.util.List;
import test.krunoslav.Spittle;


public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);

}
