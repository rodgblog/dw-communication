package dwcomm.generator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NumberGenerator implements IGenerator<Long> {

    Random random = new Random();

    @Override
    public Long generate() {
        return random.nextLong();
    }

    @Override
    public List<Long> generate(int size) {
        return random.longs().limit(size).boxed().toList();
    }


}
