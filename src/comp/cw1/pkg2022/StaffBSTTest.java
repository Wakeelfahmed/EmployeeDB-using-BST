
package comp.cw1.pkg2022;

public class StaffBSTTest {
    public void Run() {
        testBasicInsertionAndRetrieval();   // Test Case 1: Basic Insertion and Retrieval
        testDeletionAndReinsertion();       // Test Case 2: Deletion and Re-insertion
        testsize_changing();                // Test Case 3: Size changing
        testDisplayEmptyDatabase();         // Test Case 4: Displaying an Empty Database
        testSizeAndEmptyCheck();            // Test Case 5: Testing Size and Empty Check
        testRetrieveNonExistentEmployee();  // Test Case 6: Retrieving Non-Existent Employee
        testRemoveNonExistentEmployee();    // Test Case 7: Removing Non-Existent Employee
    }
    // Test Case 1: Basic Insertion and Retrieval
    public static void testBasicInsertionAndRetrieval() {
        StaffBST StaffBST = new StaffBST();
        System.out.println("\nTest 1");
        Employee employee1 = new Employee("John, Doe", "HR");
        Employee employee2 = new Employee("Jane, Smith", "Finance");

        System.out.println("Inserting \"John, Doe\", HR\"\t\t");
        StaffBST.put(employee1);
        System.out.println("Inserting \"Jane, Smith, Finance\"\t\t");
        StaffBST.put(employee2);

        Employee retrieved1 = StaffBST.get("John, Doe");
        Employee retrieved2 = StaffBST.get("Jane, Smith");

        System.out.print("Retrieving \"John, Doe\"" + "\t\tGot -> \t");
        System.out.println(retrieved1.toString());

        System.out.print("Retrieving \"Jane, Smith\"" + "\t\tGot -> \t");
        System.out.println(retrieved2.toString());

        if (retrieved1 != null && retrieved1.equals(employee1) && retrieved2 != null && retrieved2.equals(employee2))
            System.out.println("Test Case 1 (Basic Insertion and Retrieval): Passed");
        else
            System.out.println("Test Case 1 (Basic Insertion and Retrieval): FAILED");
    }

    // Test Case 2: Deletion and Re-insertion
    public static void testDeletionAndReinsertion() {
        System.out.println("\nTest 2");
        StaffBST StaffBST = new StaffBST();
        Employee employee = new Employee("Alice, Johnson", "Marketing");

        System.out.println("Inserting \"Alice, Johnson, Marketing\"");
        StaffBST.put(employee);

        System.out.println("Deleting \"Alice, Johnson\"");
        Employee deleted = StaffBST.remove("Alice, Johnson");

        System.out.println("Deleting \"Unknown, Employee\"");
        Employee notFound = StaffBST.remove("Unknown, Employee");

        System.out.print("Unknown, Employee");
        if (notFound != null)
            System.out.println(" deleted");
        else
            System.out.println(" not found");

        if (deleted != null && deleted.equals(employee) && notFound == null) {
            // Test re-insertion
            Employee reinserted = new Employee("Alice, Johnson", "Sales");
            System.out.println("Inserting \"Alice, Johnson, Sales\"");
            StaffBST.put(reinserted);

            Employee retrieved = StaffBST.get("Alice, Johnson");
            System.out.print("Retrieving \"Alice, Johnson\"\t\tGot -> ");
            System.out.println(retrieved.toString());

            if (retrieved != null && retrieved.equals(reinserted))
                System.out.println("Test Case 2 (Deletion and Re-insertion): Passed");
             else
                System.out.println("Test Case 2 (Deletion and Re-insertion): FAILED (Re-insertion)");

        } else
            System.out.println("Test Case 2 (Deletion and Re-insertion): FAILED");
    }

    // Test Case 3: Size Changing
    public static void testsize_changing() {
        System.out.println("\nTest 3");
        StaffBST StaffBST = new StaffBST();

        Employee employee1 = new Employee("John, Doe", "HR");
        Employee employee2 = new Employee("Jane, Smith", "Finance");
        Employee employee3 = new Employee("Alice, Johnson", "Marketing");
        Employee employee4 = new Employee("Bob, Brown", "Sales");

        System.out.println("Tree size at Start :" + StaffBST.size());

        System.out.println("Inserting \"John, Doe, HR\"\t\t");
        StaffBST.put(employee1);
        System.out.println("Inserting \"Jane, Smith, Finance\"\t\t");
        StaffBST.put(employee2);
        System.out.println("Inserting \"Alice, Johnson, Marketing\"\t\t");
        StaffBST.put(employee3);
        System.out.println("Inserting \"Bob, Brown, Sales\"\t\t");
        StaffBST.put(employee4);

        System.out.println("Tree size after 4 records inserted :" + StaffBST.size());

        if (StaffBST.size() == 4 && StaffBST.get("John, Doe") != null)
            System.out.println("Test Case 3 (Size changing): Passed");
        else
            System.out.println("Test Case 3 (Size changing): FAILED");
    }

    // Test Case 5: Displaying an Empty Database
    public static void testDisplayEmptyDatabase() {
        System.out.println("\nTest 4");
        StaffBST StaffBST = new StaffBST();
        StaffBST.displayDB();
        System.out.println("Test Case 4 (Displaying an Empty Database): Passed (Visual Check)");
    }
    // Test Case 6: Testing Size and Empty Check
    public static void testSizeAndEmptyCheck() {
        System.out.println("\nTest 5");
        StaffBST StaffBST = new StaffBST();

        // Initially, the Tree table is empty
        if (StaffBST.isEmpty() && StaffBST.isEmpty())
            System.out.println("Test Case 5 (Size and Empty Check - Empty Table): Passed");
        else
            System.out.println("Test Case 5 (Size and Empty Check - Empty Table): FAILED");

        // Insert a single employee and check size and emptiness
        Employee employee = new Employee("Alice, Johnson", "Marketing");
        System.out.println("Inserting \"John, Doe\", HR\"");
        StaffBST.put(employee);

        if (StaffBST.size() == 1 && !StaffBST.isEmpty())
            System.out.println("Test Case 5 (Size and Empty Check - Non-empty Table): Passed");
        else
            System.out.println("Test Case 5 (Size and Empty Check - Non-empty Table): FAILED");
    }
    // Test Case 7: Retrieving Non-Existent Employee
    public static void testRetrieveNonExistentEmployee() {
        System.out.println("\nTest 6");
        StaffBST StaffBST = new StaffBST();

        // Try to retrieve an employee that doesn't exist in the table
        Employee retrieved = StaffBST.get("Nonexistent, Employee");
        System.out.print("Retrieving \"Nonexistent, Employee\"\t\tGot ->\t");
        if (retrieved == null) {
            System.out.println("Not Found");
            System.out.println("Test Case 6 (Retrieving Non-Existent Employee): Passed");
        } else {
            System.out.println("Found");
            System.out.println("Test Case 6 (Retrieving Non-Existent Employee): FAILED");
        }
    }
    // Test Case 8: Removing Non-Existent Employee
    public static void testRemoveNonExistentEmployee() {
        System.out.println("\nTest 7");
        StaffBST StaffBST = new StaffBST();

        // Try to remove an employee that doesn't exist in the table
        Employee removed = StaffBST.remove("Nonexistent, Employee");

        if (removed == null)
            System.out.println("Test Case 7 (Removing Non-Existent Employee): Passed");
        else
            System.out.println("Test Case 7 (Removing Non-Existent Employee): FAILED");
    }
}

