package checkstyle.reproducer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UnusedImportWorks {

    private static final Set<String> FOO;
    static {
        FOO = new HashSet<>();

        FOO.add( HashMap.class.getName() );
    }
}
