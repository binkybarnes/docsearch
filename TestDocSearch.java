import static org.junit.Assert.*;
import java.io.File;
import org.junit.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

public class TestDocSearch {
    @Test
    public void testIndex() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        URI rootPath = new URI("http://localhost/");
        assertEquals("There are 1391 total files to search.", h.handleRequest(rootPath));
    }

    @Test
    public void testSearch() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        String sep = File.separator;
        URI rootPath = new URI("http://localhost/search?q=Resonance");
        String expect = String.format(
                "Found 2 paths:\n.%stechnical%sbiomed%sar615.txt\n.%stechnical%splos%sjournal.pbio.0020150.txt", sep,
                sep, sep, sep, sep, sep);
        assertEquals(expect, h.handleRequest(rootPath));
    }

    @Test
    public void testSearch2() throws URISyntaxException, IOException {

        // grep -l -r "RAA" technical ─╯
        // technical/biomed/1477-7827-1-13.txt
        // technical/911report/chapter-13.2.txt
        Handler h = new Handler("./technical/");
        String sep = File.separator;
        URI rootPath = new URI("http://localhost/search?q=RAA");
        String expect = String.format(
                "Found 2 paths:\n.%1$stechnical%1$s911report%1$schapter-13.2.txt\n.%1$stechnical%1$sbiomed%1$s1477-7827-1-13.txt",
                sep);
        assertEquals(expect, h.handleRequest(rootPath));
    }
}
