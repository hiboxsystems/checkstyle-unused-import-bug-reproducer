# checkstyle-unused-import-bug-reproducer

With Checkstyle 8.44, 8.45, 8.45.1 and 9.0 and 9.0.1:

```
$ ./gradlew checkstyleMain

> Task :checkstyleMain FAILED
[ant:checkstyle] [ERROR] /home/slovdahl/checkstyle-unused-import-bug-reproducer/src/main/java/checkstyle/reproducer/UnusedImportBug.java:3:8: Unused import - java.util.HashMap. [UnusedImports]

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':checkstyleMain'.
> Checkstyle rule violations were found. See the report at: file:///home/slovdahl/checkstyle-unused-import-bug-reproducer/build/reports/checkstyle/main.html
  Checkstyle files with violations: 1
  Checkstyle violations by severity: [error:1]


* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 712ms
2 actionable tasks: 2 executed
```

With Checkstyle 8.43 and older:

```
$ ./gradlew checkstyleMain

BUILD SUCCESSFUL in 803ms
2 actionable tasks: 1 executed, 1 up-to-date
```

## Problem

```java
package checkstyle.reproducer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UnusedImportBug {

    private static final Set<String> FOO;
    static {
        FOO = new HashSet<>();

        FOO.add( HashMap[].class.getName() );
    }
}
```

Having the only reference of a class be an array like `HashMap[]` seems to trigger an "unused import" for that class.
