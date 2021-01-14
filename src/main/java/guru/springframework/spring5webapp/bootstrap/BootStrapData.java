package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Comm");
        publisher.setCity("Moscow");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher count: " + publisherRepository.count());

        Author karl = new Author("Karl", "Marx");
        Author trotski = new Author("Leo", "Trotski");

        Book dasKapital = new Book("Das Kapital", "12345");
        Book theRevo = new Book("the Revolution Betrayed", "56789");

        dasKapital.setPublisher(publisher);
        theRevo.setPublisher(publisher);

        publisher.getBooks().add(dasKapital);
        publisher.getBooks().add(theRevo);

        karl.getBooks().add(dasKapital);
        trotski.getBooks().add(theRevo);

        dasKapital.getAuthors().add(karl);
        theRevo.getAuthors().add(trotski);

        authorRepository.save(karl);
        authorRepository.save(trotski);
        bookRepository.save(dasKapital);
        bookRepository.save(theRevo);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
