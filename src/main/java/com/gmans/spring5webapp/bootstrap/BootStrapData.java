package com.gmans.spring5webapp.bootstrap;

import com.gmans.spring5webapp.domain.Author;
import com.gmans.spring5webapp.domain.Book;
import com.gmans.spring5webapp.domain.Publisher;
import com.gmans.spring5webapp.repositories.AuthorRepository;
import com.gmans.spring5webapp.repositories.BookRepository;
import com.gmans.spring5webapp.repositories.PublisherRepository;
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

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("AM Publishers");
        publisher.setCity("Hyderabad");
        publisher.setState("Telangana");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: "+publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "123456");
        eric.getBooks().add(noEJB);
        ddd.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: "+ publisher.getBooks().size());

    }
}
