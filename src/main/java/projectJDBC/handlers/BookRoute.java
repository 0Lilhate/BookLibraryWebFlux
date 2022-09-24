package projectJDBC.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import projectJDBC.dto.BookDto;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.service.ParsingDtoBook;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Configuration
public class BookRoute {

    //    @Bean
//    public RouterFunction<ServerResponse> composedRoutes(BookRepository repository){
//        return route()
//                .GET("/book/all", accept(APPLICATION_JSON)
//                    ,request -> ok().contentType(APPLICATION_JSON).body(repository.findAll(), BookDto.class))
//
//                .GET("/book/{id}", accept(APPLICATION_JSON),
//                        request -> ok().contentType(APPLICATION_JSON)
//                                .body(repository.findById(request.pathVariable("id")), BookDto.class))
////                .DELETE("/book/delete/{id}", accept(APPLICATION_JSON),
////                        request -> ok().contentType(APPLICATION_JSON)
////                                .body(repository.deleteById(request.pathVariable("id")), BookDto.class))
////                .DELETE("/book/delete/{id}", accept(APPLICATION_JSON), repository)
//
//                //.PUT("/book/change", accept(APPLICATION_JSON),
////                        request -> ok().contentType(APPLICATION_JSON)
////                                .body(repository.updateBookNameById(request.requestPath())))
//                .build();
//
//
//    }

    @Bean
    public RouterFunction<ServerResponse> routes(BookHandler bookHandler){
        return route(GET("/book"), bookHandler::getAllBook)
                .andRoute(GET("/book/{id}"), bookHandler::getBookById)
                .andRoute(DELETE("/book/delete/{id}"), bookHandler::deleteBookById)
                .andRoute(PUT("/book/change/{id}"), bookHandler::updateBook)
                .andRoute(POST("/book"), bookHandler::createBook);



    }

}
