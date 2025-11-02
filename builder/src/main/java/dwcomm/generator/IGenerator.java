package dwcomm.generator;

import java.util.List;

public interface IGenerator<ContentType> {
    ContentType generate();
    List<ContentType> generate(int size);
}
