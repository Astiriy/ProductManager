import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product item1 = new Book(11, "Мастер", 100, "Михаил");
    Product item2 = new Book(22, "Маргарита", 200, "Булгаков");
    Product item3 = new Book(333, "Мастер и Маргарита", 300, "Михаил Булгаков");
    Product item4 = new Smartphone(4444, "Apple", 40_000, "США");
    Product item5 = new Smartphone(55555, "Samsung", 50_000, "Южная Корея");
    Product item6 = new Smartphone(666666, "Xiaomi", 60_000, "Китай");

    @BeforeEach
    public void Product() {
        repo.save(item1);
        repo.save(item2);
        repo.save(item3);
        repo.save(item4);
        repo.save(item5);
        repo.save(item6);
    }

    @Test
    public void RepositorySave() {

        Product[] expected = {item1, item2, item3, item4, item5, item6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void RepositoryRemoveById() {

        repo.removeById(22);

        Product[] expected = {item1, item3, item4, item5, item6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ManagerSearchBook() {

        Product[] actual = manager.searchBy("Мастер");
        Product[] expected = {item1, item3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ManagerSearchSmartphone() {

        Product[] actual = manager.searchBy("App");
        Product[] expected = {item4};

        Assertions.assertArrayEquals(expected, actual);
    }
}
