package repository;

import model.Book;
import util.MyList;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

/*Слой репозитория (Repository Layer)**:
        - Отвечает за прямую работу с данными (в вашем случае это, возможно, структуры данных,
                                               такие как `RubberArray`(MyArrayList) или `MyLinkedList`).
        - Методы для добавления, удаления, обновления и извлечения данных.
*/
public interface InterfaceRepository {

 void addBook(int idBook);

 void removeBook(int idBook);

MyList<Book> findAllBook();//TODO

 MyList<Book> findTitteBook();

 MyList<Book> findAuthor();

boolean listOfBook(int idBook);

int findBookBeiId(int idBook);




}
