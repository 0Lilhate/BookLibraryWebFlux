//package projectJDBC.service;
//
//
//import org.springframework.stereotype.Service;
//
//
//import org.springframework.transaction.annotation.Transactional;
//import projectJDBC.controler.ControlMessenger;
//import projectJDBC.domain.Author;
//import projectJDBC.domain.Book;
//import projectJDBC.domain.Comment;
//import projectJDBC.domain.Genre;
//import projectJDBC.repository.author.AuthorRepository;
//import projectJDBC.repository.book.BookRepository;
//import projectJDBC.repository.comment.CommentRepository;
//import projectJDBC.repository.genre.GenreRepository;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//@Service
//public class GetDateServiceJDBC implements GetDateService {
//
//    private BookRepository bookRepository;
//    private ControlMessenger controlMessenger;
//    private AuthorRepository authorRepository;
//    private GenreRepository genreRepository;
//    private CommentRepository commentRepository;
//
//
//    public GetDateServiceJDBC(BookRepository bookRepository, AuthorRepository authorRepository,
//                              GenreRepository genreRepository, CommentRepository commentRepository, ControlMessenger controlMessenger) {
//        this.bookRepository = bookRepository;
//        this.controlMessenger = controlMessenger;
//        this.authorRepository = authorRepository;
//        this.genreRepository = genreRepository;
//        this.commentRepository = commentRepository;
//    }
//
//
//
//    @Override
//    @Transactional
//    public Book insertBook() {
//
////        controlMessenger.greetingString("Введите название книги: ");
////        String nameBook = controlMessenger.readLine();
////
////        controlMessenger.greetingString("Введите год выпуска книги: ");
////        String dateBook = controlMessenger.readLine();
////
////        controlMessenger.greetingString("Введите псевдоним автора книги: ");
////        String nameAuthor = controlMessenger.readLine();
////
////        controlMessenger.greetingString("Введите дату рождения автора книги: ");
////        String yearAuthor = controlMessenger.readLine();
////
////        controlMessenger.greetingString("Введите жанры книге через запятую: ");
////        String[] genresName = controlMessenger.readLine().split(",");
////        List<Genre> genreList = new ArrayList<>();
////        for (int i=0; i<genresName.length; i++){
////            genreList.add(genreRepository.getByNameOrCreate(new Genre(genresName[i])));
////        }
////
////        controlMessenger.greetingString("Введите комментарий к книге, если хотите: ");
////        String comment =  controlMessenger.readLine();
////
////        Comment commentSingleton = commentRepository.save(new Comment(comment));
////
////
////
////        Book insertBook = new Book(nameBook, dateBook,
////                authorRepository.getByNameOrCreate(new Author(nameAuthor, yearAuthor)), genreList, commentSingleton);
////        return bookRepository.save(insertBook);
//
//        return null;
//    }
//
//
//    @Transactional
//    @Override
//    public void getAllBook() {
////        List<Book> bookList = bookRepository.findAll();
////        for (Book temp : bookList) {
////           controlMessenger.greetingString(temp.toString()+"\n");
////        }
//    }
//
//    @Override
//    public void updateNameBookById() {
////        controlMessenger.greetingString("Введите название книги, которое хотите изменить: ");
////        String id = controlMessenger.readLine();
////
////        controlMessenger.greetingString("Введите название книги, на которое хотите изменить: ");
////        String nameBook = controlMessenger.readLine();
////        bookRepository.updateBookNameByName(id, nameBook);
//
//
//    }
//
//    @Transactional
//    @Override
//    public void deleteBookById() {
////        controlMessenger.greetingString("Введите название книги, которое хотите удалить: ");
////        String name = controlMessenger.readLine();
////        bookRepository.deleteBookByName(name);
//    }
//
//    @Transactional
//    @Override
//    public boolean getBookById() {
////        controlMessenger.greetingString("Введите id книги, которое хотите получить: ");
////        String id = controlMessenger.readLine();
////        Book book = bookRepository.findAll().get(Integer.valueOf(id));
////        if(book.getComments() == null){
////            controlMessenger.greetingString("{id=" + book.getId_book() + ", name = " + book.getName()
////                    + ", date = " + book.getDate() + ", author = " + book.getAuthor().getAuthorName() +
////                    "genre = " + book.getGenreList().toString() + "}\n");
////        }else {
////            controlMessenger.greetingString(book.toString()+"\n");
////        }
////        return book;
//
////        controlMessenger.greetingString("Введите название книги, которое хотите получить: ");
////        String nameBook = controlMessenger.readLine();
////        List<Book> bookList = bookRepository.findBookByName(nameBook);
////        if(!bookList.isEmpty()){
////            for (Book bookTemp : bookList) {
////                controlMessenger.greetingString(bookTemp.toString()+"\n");
////            }
////            return true;
////        }
////        else {
////            return false;
////        }
//
//
//        return false;
//    }
//
//    @Override
//    public Author test(){
////        List<Genre> genreList = new ArrayList<>();
////        genreList.add(genreRepository.getByNameOrCreate(new Genre("Drama")));
////        genreList.add(genreRepository.getByNameOrCreate(new Genre("Romans")));
////
////        List<Comment> commentList = new ArrayList<>();
////        commentList.add(commentRepository.save(new Comment("yes")));
////        commentList.add(commentRepository.save(new Comment("not")));
////        bookRepository.insert(new Book( "xdad", "1999-05-11",
////                authorRepository.getByNameOrCreate(new Author("pppp", "2008-09-12")), genreList,
////                commentList));
//
////        Mono<Author> authorMono = authorRepository.getByNameOrCreate(new Author("aaaa", "2008-09-12"));
//
//        Book book = new Book("2", "adad", "131231", new Genre("Romans"),
//                new Author("Lermontov", "3113"), null);
//
//
//
//        return null;
//    }
//}
