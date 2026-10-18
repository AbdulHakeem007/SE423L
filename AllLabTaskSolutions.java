import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AllLabTaskSolutions {

    public static void main(String[] args) {
        // Uncomment one line to run a single section, or leave runAll() enabled.

        // runLabTask01();
        // runLabTask02();
        // runLabTask03A();
        // runLabTask03B();
        // runLab04FactoryMethod();
        // runLab04AbstractFactory();
        // runLab04Builder();
        // runLab04Prototype();
        // runLab04Singleton();
        // runLab05Adapter();
        // runLab05Decorator();
        // runLab05Facade();
        // runLab05Composite();
        // runLab05Proxy();
        // runLab06Strategy();
        // runLab06Observer();
        // runLab06Command();
        // runLab06TemplateMethod();
        // runLab06State();
        // runLab06Iterator();
        // runLab06Decoupling();

        runAll();
    }

    static void runAll() {
        runLabTask01();
        runLabTask02();
        runLabTask03A();
        runLabTask03B();
        runLab04FactoryMethod();
        runLab04AbstractFactory();
        runLab04Builder();
        runLab04Prototype();
        runLab04Singleton();
        runLab05Adapter();
        runLab05Decorator();
        runLab05Facade();
        runLab05Composite();
        runLab05Proxy();
        runLab06Strategy();
        runLab06Observer();
        runLab06Command();
        runLab06TemplateMethod();
        runLab06State();
        runLab06Iterator();
        runLab06Decoupling();
    }

    // =====================================================================
    // LAB TASK 01
    // =====================================================================

    static void runLabTask01() {
        System.out.println("\n=== LAB TASK 01 ===");
        T01_Book book = new T01_Book("Clean Code", "Robert Martin");
        T01_Member member = new T01_Member("Ali");
        T01_LibraryService library = new T01_LibraryService();
        library.addBook(book);
        library.registerMember(member);
        library.borrowBook(member, book);
        library.displayStatus(member, book);
    }

    // =====================================================================
    // LAB TASK 02
    // =====================================================================

    static void runLabTask02() {
        System.out.println("\n=== LAB TASK 02 ===");
        T02_Ride ride = new T02_Ride("Giki", "Islamabad", "Sedan");
        T02_RideService service = new T02_RideService(new T02_FareCalculator());
        service.bookRide(ride);
        service.displayRide(ride);
        service.updateStatus(ride, "In Progress");
        service.displayRide(ride);
        service.updateStatus(ride, "Completed");
        service.displayRide(ride);
    }

    // =====================================================================
    // LAB TASK 03 A
    // =====================================================================

    static void runLabTask03A() {
        System.out.println("\n=== LAB TASK 03 A ===");
        T03A_PatientRepository patientRepo = new T03A_InMemoryPatientRepository();
        T03A_DoctorRepository doctorRepo = new T03A_InMemoryDoctorRepository();
        T03A_AppointmentService appointmentService =
                new T03A_AppointmentService(new T03A_SmsNotification());
        T03A_BillingService billingService = new T03A_BillingService();
        T03A_HospitalService hospital = new T03A_HospitalService(
                patientRepo, doctorRepo, appointmentService, billingService,
                new T03A_CardPayment(), new T03A_SmsNotification());

        T03A_Patient patient = hospital.registerPatient(1, "Ali", 35, "Fever");
        T03A_Doctor doctor = hospital.registerDoctor(101, "Ahmed", "General Physician");
        hospital.scheduleAppointment(patient, doctor, "25-09-2026");
        double bill = hospital.generateBill(patient, 3000, 2000);
        hospital.processPayment(patient, bill);
        hospital.sendNotification(patient, "Your appointment is confirmed.");
        hospital.findPatient(1);
        hospital.savePatientRecord(patient);
    }

    // =====================================================================
    // LAB TASK 03 B
    // =====================================================================

    static void runLabTask03B() {
        System.out.println("\n=== LAB TASK 03 B ===");
        T03B_BookRepository bookRepo = new T03B_InMemoryBookRepository();
        T03B_MemberRepository memberRepo = new T03B_InMemoryMemberRepository();
        T03B_FineCalculator fineCalculator = new T03B_StandardFineCalculator();
        T03B_NotificationService notification = new T03B_EmailNotification();
        T03B_LibraryStorage storage = new T03B_MySqlLibraryStorage();
        T03B_BorrowingService borrowing =
                new T03B_BorrowingService(bookRepo, fineCalculator, notification, storage);
        T03B_LibraryFacade library =
                new T03B_LibraryFacade(bookRepo, memberRepo, borrowing, notification, storage);

        library.addBook("Clean Code", "Robert Martin");
        library.registerMember("Ali", "regular");
        library.borrowBook("Ali", "Clean Code");
        library.returnBook("Ali", "Clean Code");
        System.out.println("Fine: " + library.calculateFine(7));
        library.sendNotification("Book returned successfully.");
        library.saveData();
        library.printReport();
    }

    // =====================================================================
    // LAB 04 — Factory Method
    // =====================================================================

    static void runLab04FactoryMethod() {
        System.out.println("\n=== LAB 04 FACTORY METHOD ===");
        L04_Delivery delivery = L04_DeliveryFactory.createDelivery("drone");
        delivery.deliver();
        L04_DeliveryFactory.createDelivery("bike").deliver();
        L04_DeliveryFactory.createDelivery("car").deliver();
    }

    // =====================================================================
    // LAB 04 — Abstract Factory
    // =====================================================================

    static void runLab04AbstractFactory() {
        System.out.println("\n=== LAB 04 ABSTRACT FACTORY ===");
        L04_DeliveryServiceFactory online = new L04_OnlineOrderFactory();
        online.createPayment().pay();
        online.createNotification().send();

        L04_DeliveryServiceFactory local = new L04_LocalOrderFactory();
        local.createPayment().pay();
        local.createNotification().send();
    }

    // =====================================================================
    // LAB 04 — Builder
    // =====================================================================

    static void runLab04Builder() {
        System.out.println("\n=== LAB 04 BUILDER ===");
        L04_FoodOrder order = new L04_FoodOrder.Builder("Ali", "Pizza House")
                .setAddress("Islamabad")
                .setPaymentMethod("Credit Card")
                .setInstructions("Deliver after 7 PM")
                .build();
        order.displayOrder();
    }

    // =====================================================================
    // LAB 04 — Prototype
    // =====================================================================

    static void runLab04Prototype() {
        System.out.println("\n=== LAB 04 PROTOTYPE ===");
        L04_MenuItem original = new L04_MenuItem("Chicken Burger", 8.0);
        L04_MenuItem copy = original.clone();
        copy.setPrice(7.0);
        original.display();
        copy.display();
    }

    // =====================================================================
    // LAB 04 — Singleton
    // =====================================================================

    static void runLab04Singleton() {
        System.out.println("\n=== LAB 04 SINGLETON ===");
        L04_AppConfig config1 = L04_AppConfig.getInstance();
        L04_AppConfig config2 = L04_AppConfig.getInstance();
        config1.displayMessage();
        System.out.println("Same instance: " + (config1 == config2));
    }

    // =====================================================================
    // LAB 05 — Adapter Task (MobileWallet adapter)
    // =====================================================================

    static void runLab05Adapter() {
        System.out.println("\n=== LAB 05 ADAPTER ===");
        L05_PaymentProcessor card =
                new L05_PaymentAdapter(new L05_ThirdPartyPayment());
        card.pay(2500);

        L05_PaymentProcessor wallet =
                new L05_MobileWalletAdapter(new L05_MobileWallet());
        wallet.pay(1500);
    }

    // =====================================================================
    // LAB 05 — Decorator Task (Fries + Drink)
    // =====================================================================

    static void runLab05Decorator() {
        System.out.println("\n=== LAB 05 DECORATOR ===");
        L05_Food order = new L05_Burger();
        order = new L05_CheeseDecorator(order);
        order = new L05_SauceDecorator(order);
        order = new L05_FriesDecorator(order);
        order = new L05_DrinkDecorator(order);
        System.out.println(order.getDescription());
        System.out.println("Price: $" + order.getPrice());
    }

    // =====================================================================
    // LAB 05 — Facade Task (TrackingService)
    // =====================================================================

    static void runLab05Facade() {
        System.out.println("\n=== LAB 05 FACADE ===");
        new L05_FoodDeliveryFacade().placeOrder();
    }

    // =====================================================================
    // LAB 05 — Composite Task (menu hierarchy)
    // =====================================================================

    static void runLab05Composite() {
        System.out.println("\n=== LAB 05 COMPOSITE ===");
        L05_MenuCategory menu = new L05_MenuCategory("Food Delivery Menu");

        L05_MenuCategory pakistani = new L05_MenuCategory("Pakistani");
        pakistani.add(new L05_FoodItem("Biryani"));
        pakistani.add(new L05_FoodItem("Karahi"));

        L05_MenuCategory fastFood = new L05_MenuCategory("Fast Food");
        fastFood.add(new L05_FoodItem("Burger"));
        fastFood.add(new L05_FoodItem("Pizza"));

        L05_MenuCategory desserts = new L05_MenuCategory("Desserts");
        desserts.add(new L05_FoodItem("Cake"));
        desserts.add(new L05_FoodItem("Ice Cream"));

        menu.add(pakistani);
        menu.add(fastFood);
        menu.add(desserts);
        menu.show();
    }

    // =====================================================================
    // LAB 05 — Proxy Task (auth + lazy create)
    // =====================================================================

    static void runLab05Proxy() {
        System.out.println("\n=== LAB 05 PROXY ===");
        L05_DeliveryService denied = new L05_DeliveryProxy(false);
        denied.trackOrder();
        L05_DeliveryService allowed = new L05_DeliveryProxy(true);
        allowed.trackOrder();
    }

    // =====================================================================
    // LAB 06 — Strategy Practice (PremiumDelivery)
    // =====================================================================

    static void runLab06Strategy() {
        System.out.println("\n=== LAB 06 STRATEGY ===");
        L06_DeliveryService service = new L06_DeliveryService();
        service.setStrategy(new L06_StandardDelivery());
        System.out.println("Standard: " + service.getFee(5));
        service.setStrategy(new L06_ExpressDelivery());
        System.out.println("Express: " + service.getFee(5));
        service.setStrategy(new L06_PremiumDelivery());
        System.out.println("Premium: " + service.getFee(5));
    }

    // =====================================================================
    // LAB 06 — Observer Practice (RestaurantManager + remove)
    // =====================================================================

    static void runLab06Observer() {
        System.out.println("\n=== LAB 06 OBSERVER ===");
        L06_Order order = new L06_Order();
        L06_Customer customer = new L06_Customer();
        L06_DeliveryRider rider = new L06_DeliveryRider();
        L06_RestaurantManager manager = new L06_RestaurantManager();

        order.addObserver(customer);
        order.addObserver(rider);
        order.addObserver(manager);
        order.setStatus("Preparing");

        order.removeObserver(manager);
        order.setStatus("Out for delivery");
    }

    // =====================================================================
    // LAB 06 — Command Practice (PrepareOrderCommand)
    // =====================================================================

    static void runLab06Command() {
        System.out.println("\n=== LAB 06 COMMAND ===");
        L06_FoodOrder order = new L06_FoodOrder();
        L06_RestaurantController controller = new L06_RestaurantController();
        controller.executeCommand(new L06_AcceptOrderCommand(order));
        controller.executeCommand(new L06_PrepareOrderCommand(order));
        controller.executeCommand(new L06_CancelOrderCommand(order));
    }

    // =====================================================================
    // LAB 06 — Template Method Practice (WalletPayment)
    // =====================================================================

    static void runLab06TemplateMethod() {
        System.out.println("\n=== LAB 06 TEMPLATE METHOD ===");
        new L06_CashPayment().processPayment();
        System.out.println("---");
        new L06_CardPayment().processPayment();
        System.out.println("---");
        new L06_WalletPayment().processPayment();
    }

    // =====================================================================
    // LAB 06 — State Practice (ReadyForPickupState)
    // =====================================================================

    static void runLab06State() {
        System.out.println("\n=== LAB 06 STATE ===");
        L06_OrderContext order = new L06_OrderContext();
        order.showStatus();
        order.nextState();
        order.showStatus();
        order.nextState();
        order.showStatus();
        order.nextState();
        order.showStatus();
        order.nextState();
    }

    // =====================================================================
    // LAB 06 — Iterator Practice (5 items + LinkedList)
    // =====================================================================

    static void runLab06Iterator() {
        System.out.println("\n=== LAB 06 ITERATOR ===");
        L06_RestaurantMenu menu = new L06_RestaurantMenu();
        menu.addItem(new L06_MenuItem("Burger"));
        menu.addItem(new L06_MenuItem("Pizza"));
        menu.addItem(new L06_MenuItem("Sandwich"));
        menu.addItem(new L06_MenuItem("Biryani"));
        menu.addItem(new L06_MenuItem("Salad"));
        for (L06_MenuItem item : menu) {
            System.out.println(item.getName());
        }
    }

    // =====================================================================
    // LAB 06 — Decoupling Algorithms and Control Flow
    // =====================================================================

    static void runLab06Decoupling() {
        System.out.println("\n=== LAB 06 DECOUPLING ===");
        L06_DeliveryProcessor processor = new L06_DeliveryProcessor();
        processor.processDelivery(new L06_StandardDelivery(), 5);
        System.out.println("---");
        processor.processDelivery(new L06_ExpressDelivery(), 5);
        System.out.println("---");
        processor.processDelivery(new L06_PremiumDelivery(), 5);
    }
}

// =====================================================================
// LAB TASK 01 classes
// =====================================================================

class T01_Book {
    private String title;
    private String author;
    private boolean available = true;

    public T01_Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}

class T01_Member {
    private String name;
    public T01_Member(String name) { this.name = name; }
    public String getName() { return name; }
}

class T01_LibraryService {
    private List<T01_Book> books = new ArrayList<>();
    private List<T01_Member> members = new ArrayList<>();

    public void addBook(T01_Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void registerMember(T01_Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public void borrowBook(T01_Member member, T01_Book book) {
        if (!book.isAvailable()) {
            System.out.println("Book not available.");
            return;
        }
        book.setAvailable(false);
        System.out.println(member.getName() + " borrowed " + book.getTitle());
    }

    public void displayStatus(T01_Member member, T01_Book book) {
        System.out.println("Member: " + member.getName());
        System.out.println("Book: " + book.getTitle() + " | Available: " + book.isAvailable());
    }
}

// =====================================================================
// LAB TASK 02 classes
// =====================================================================

class T02_Ride {
    private String pickup;
    private String destination;
    private String rideType;
    private double fare;
    private String status;

    public T02_Ride(String pickup, String destination, String rideType) {
        if (pickup == null || pickup.isBlank()) throw new IllegalArgumentException("Pickup empty");
        if (destination == null || destination.isBlank()) throw new IllegalArgumentException("Destination empty");
        if (rideType == null || rideType.isBlank()) throw new IllegalArgumentException("Ride type empty");
        this.pickup = pickup;
        this.destination = destination;
        this.rideType = rideType;
        this.status = "Requested";
    }

    public String getPickup() { return pickup; }
    public String getDestination() { return destination; }
    public String getRideType() { return rideType; }
    public double getFare() { return fare; }
    public String getStatus() { return status; }

    public void setFare(double fare) {
        if (fare < 0) throw new IllegalArgumentException("Fare negative");
        this.fare = fare;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) throw new IllegalArgumentException("Status empty");
        this.status = status;
    }
}

class T02_FareCalculator {
    public double calculate(String rideType) {
        if (rideType.equalsIgnoreCase("Bike")) return 200;
        if (rideType.equalsIgnoreCase("Sedan")) return 500;
        if (rideType.equalsIgnoreCase("SUV")) return 800;
        return 400;
    }
}

class T02_RideService {
    private T02_FareCalculator fareCalculator;

    public T02_RideService(T02_FareCalculator fareCalculator) {
        this.fareCalculator = fareCalculator;
    }

    public void bookRide(T02_Ride ride) {
        ride.setFare(fareCalculator.calculate(ride.getRideType()));
        ride.setStatus("Booked");
        System.out.println("Ride booked successfully.");
    }

    public void updateStatus(T02_Ride ride, String status) {
        ride.setStatus(status);
        System.out.println("Ride status updated to: " + status);
    }

    public void displayRide(T02_Ride ride) {
        System.out.println("Pickup: " + ride.getPickup());
        System.out.println("Destination: " + ride.getDestination());
        System.out.println("Ride Type: " + ride.getRideType());
        System.out.println("Fare: " + ride.getFare());
        System.out.println("Status: " + ride.getStatus());
    }
}

// =====================================================================
// LAB TASK 03 A classes
// =====================================================================

class T03A_Patient {
    private int id;
    private String name;
    private int age;
    private String disease;

    public T03A_Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDisease() { return disease; }
}

class T03A_Doctor {
    private int id;
    private String name;
    private String specialization;

    public T03A_Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
}

class T03A_Appointment {
    private T03A_Patient patient;
    private T03A_Doctor doctor;
    private String date;

    public T03A_Appointment(T03A_Patient patient, T03A_Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public T03A_Patient getPatient() { return patient; }
    public T03A_Doctor getDoctor() { return doctor; }
    public String getDate() { return date; }
}

interface T03A_PatientManagement {
    T03A_Patient registerPatient(int id, String name, int age, String disease);
    void findPatient(int id);
    void savePatientRecord(T03A_Patient patient);
}

interface T03A_AppointmentManagement {
    void scheduleAppointment(T03A_Patient patient, T03A_Doctor doctor, String date);
}

interface T03A_BillingManagement {
    double generateBill(T03A_Patient patient, double consultationFee, double medicineFee);
}

interface T03A_NotificationService {
    void notify(T03A_Patient patient, String message);
}

interface T03A_PaymentMethod {
    void pay(T03A_Patient patient, double amount);
}

interface T03A_PatientRepository {
    void save(T03A_Patient patient);
    T03A_Patient findById(int id);
}

interface T03A_DoctorRepository {
    void save(T03A_Doctor doctor);
}

class T03A_InMemoryPatientRepository implements T03A_PatientRepository {
    private List<T03A_Patient> patients = new ArrayList<>();

    public void save(T03A_Patient patient) {
        patients.add(patient);
        System.out.println("Saving patient record in database.");
    }

    public T03A_Patient findById(int id) {
        for (T03A_Patient p : patients) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}

class T03A_InMemoryDoctorRepository implements T03A_DoctorRepository {
    private List<T03A_Doctor> doctors = new ArrayList<>();

    public void save(T03A_Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Saving doctor record in database.");
    }
}

class T03A_SmsNotification implements T03A_NotificationService {
    public void notify(T03A_Patient patient, String message) {
        System.out.println("SMS to " + patient.getName() + ": " + message);
    }
}

class T03A_EmailNotification implements T03A_NotificationService {
    public void notify(T03A_Patient patient, String message) {
        System.out.println("Email to " + patient.getName() + ": " + message);
    }
}

class T03A_WhatsAppNotification implements T03A_NotificationService {
    public void notify(T03A_Patient patient, String message) {
        System.out.println("WhatsApp to " + patient.getName() + ": " + message);
    }
}

class T03A_CashPayment implements T03A_PaymentMethod {
    public void pay(T03A_Patient patient, double amount) {
        System.out.println("Cash payment processed for " + patient.getName() + ": $" + amount);
    }
}

class T03A_CardPayment implements T03A_PaymentMethod {
    public void pay(T03A_Patient patient, double amount) {
        System.out.println("Credit card payment processed for " + patient.getName() + ": $" + amount);
    }
}

class T03A_PayPalPayment implements T03A_PaymentMethod {
    public void pay(T03A_Patient patient, double amount) {
        System.out.println("PayPal payment processed for " + patient.getName() + ": $" + amount);
    }
}

class T03A_BankPayment implements T03A_PaymentMethod {
    public void pay(T03A_Patient patient, double amount) {
        System.out.println("Bank transfer processed for " + patient.getName() + ": $" + amount);
    }
}

class T03A_AppointmentService implements T03A_AppointmentManagement {
    private List<T03A_Appointment> appointments = new ArrayList<>();
    private T03A_NotificationService notificationService;

    public T03A_AppointmentService(T03A_NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void scheduleAppointment(T03A_Patient patient, T03A_Doctor doctor, String date) {
        appointments.add(new T03A_Appointment(patient, doctor, date));
        System.out.println("Appointment scheduled for " + patient.getName()
                + " with Dr. " + doctor.getName() + " on " + date);
        notificationService.notify(patient, "Appointment on " + date);
    }
}

class T03A_BillingService implements T03A_BillingManagement {
    public double generateBill(T03A_Patient patient, double consultationFee, double medicineFee) {
        double total = consultationFee + medicineFee;
        System.out.println("Generating bill for " + patient.getName());
        System.out.println("Consultation fee: $" + consultationFee);
        System.out.println("Medicine fee: $" + medicineFee);
        System.out.println("Total bill: $" + total);
        return total;
    }
}

class T03A_HospitalService implements T03A_PatientManagement {
    private T03A_PatientRepository patientRepository;
    private T03A_DoctorRepository doctorRepository;
    private T03A_AppointmentManagement appointmentManagement;
    private T03A_BillingManagement billingManagement;
    private T03A_PaymentMethod paymentMethod;
    private T03A_NotificationService notificationService;

    public T03A_HospitalService(
            T03A_PatientRepository patientRepository,
            T03A_DoctorRepository doctorRepository,
            T03A_AppointmentManagement appointmentManagement,
            T03A_BillingManagement billingManagement,
            T03A_PaymentMethod paymentMethod,
            T03A_NotificationService notificationService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentManagement = appointmentManagement;
        this.billingManagement = billingManagement;
        this.paymentMethod = paymentMethod;
        this.notificationService = notificationService;
    }

    public T03A_Patient registerPatient(int id, String name, int age, String disease) {
        T03A_Patient patient = new T03A_Patient(id, name, age, disease);
        patientRepository.save(patient);
        System.out.println("Patient registered: " + name);
        return patient;
    }

    public T03A_Doctor registerDoctor(int id, String name, String specialization) {
        T03A_Doctor doctor = new T03A_Doctor(id, name, specialization);
        doctorRepository.save(doctor);
        System.out.println("Doctor registered: " + name);
        return doctor;
    }

    public void scheduleAppointment(T03A_Patient patient, T03A_Doctor doctor, String date) {
        appointmentManagement.scheduleAppointment(patient, doctor, date);
    }

    public double generateBill(T03A_Patient patient, double consultationFee, double medicineFee) {
        return billingManagement.generateBill(patient, consultationFee, medicineFee);
    }

    public void processPayment(T03A_Patient patient, double amount) {
        paymentMethod.pay(patient, amount);
    }

    public void sendNotification(T03A_Patient patient, String message) {
        notificationService.notify(patient, message);
    }

    public void findPatient(int id) {
        T03A_Patient patient = patientRepository.findById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Patient found: " + patient.getName());
        System.out.println("Disease: " + patient.getDisease());
    }

    public void savePatientRecord(T03A_Patient patient) {
        System.out.println("Connecting to MySQL database...");
        System.out.println("Saving patient " + patient.getName() + " to MySQL database.");
    }
}

// =====================================================================
// LAB TASK 03 B classes
// =====================================================================

class T03B_Book {
    private String title;
    private String author;
    private boolean available = true;

    public T03B_Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}

interface T03B_LibraryMember {
    String getName();
    String getType();
    boolean canBorrow();
}

class T03B_RegularMember implements T03B_LibraryMember {
    private String name;
    public T03B_RegularMember(String name) { this.name = name; }
    public String getName() { return name; }
    public String getType() { return "regular"; }
    public boolean canBorrow() { return true; }
}

class T03B_StudentMember implements T03B_LibraryMember {
    private String name;
    public T03B_StudentMember(String name) { this.name = name; }
    public String getName() { return name; }
    public String getType() { return "student"; }
    public boolean canBorrow() { return true; }
}

class T03B_GuestMember implements T03B_LibraryMember {
    private String name;
    public T03B_GuestMember(String name) { this.name = name; }
    public String getName() { return name; }
    public String getType() { return "guest"; }
    public boolean canBorrow() { return false; }
}

interface T03B_BookManagement {
    void addBook(String title, String author);
}

interface T03B_MemberManagement {
    void registerMember(String name, String type);
}

interface T03B_BorrowingOperations {
    void borrowBook(String member, String book);
    void returnBook(String member, String book);
    double calculateFine(int lateDays);
}

interface T03B_NotificationService {
    void send(String message);
}

interface T03B_LibraryStorage {
    void save();
}

interface T03B_FineCalculator {
    double calculate(int lateDays);
}

interface T03B_BookRepository {
    void add(T03B_Book book);
    T03B_Book findByTitle(String title);
    int count();
}

interface T03B_MemberRepository {
    void add(T03B_LibraryMember member);
    T03B_LibraryMember findByName(String name);
    int count();
}

class T03B_InMemoryBookRepository implements T03B_BookRepository {
    private List<T03B_Book> books = new ArrayList<>();
    public void add(T03B_Book book) { books.add(book); }
    public T03B_Book findByTitle(String title) {
        for (T03B_Book b : books) if (b.getTitle().equals(title)) return b;
        return null;
    }
    public int count() { return books.size(); }
}

class T03B_InMemoryMemberRepository implements T03B_MemberRepository {
    private List<T03B_LibraryMember> members = new ArrayList<>();
    public void add(T03B_LibraryMember member) { members.add(member); }
    public T03B_LibraryMember findByName(String name) {
        for (T03B_LibraryMember m : members) if (m.getName().equals(name)) return m;
        return null;
    }
    public int count() { return members.size(); }
}

class T03B_StandardFineCalculator implements T03B_FineCalculator {
    public double calculate(int lateDays) {
        if (lateDays <= 0) return 0;
        if (lateDays <= 5) return lateDays * 20;
        if (lateDays <= 10) return lateDays * 30;
        return lateDays * 50;
    }
}

class T03B_StudentFineCalculator implements T03B_FineCalculator {
    public double calculate(int lateDays) {
        if (lateDays <= 0) return 0;
        return lateDays * 10;
    }
}

class T03B_EmailNotification implements T03B_NotificationService {
    public void send(String message) { System.out.println("Email: " + message); }
}

class T03B_SmsNotification implements T03B_NotificationService {
    public void send(String message) { System.out.println("SMS: " + message); }
}

class T03B_MySqlLibraryStorage implements T03B_LibraryStorage {
    public void save() { System.out.println("Saving data to MySQL..."); }
}

class T03B_BorrowingService implements T03B_BorrowingOperations {
    private T03B_BookRepository bookRepository;
    private T03B_FineCalculator fineCalculator;
    private T03B_NotificationService notificationService;
    private T03B_LibraryStorage storage;

    public T03B_BorrowingService(
            T03B_BookRepository bookRepository,
            T03B_FineCalculator fineCalculator,
            T03B_NotificationService notificationService,
            T03B_LibraryStorage storage) {
        this.bookRepository = bookRepository;
        this.fineCalculator = fineCalculator;
        this.notificationService = notificationService;
        this.storage = storage;
    }

    public void borrowBook(String member, String bookTitle) {
        T03B_Book book = bookRepository.findByTitle(bookTitle);
        if (book == null || !book.isAvailable()) {
            System.out.println("Book not available.");
            return;
        }
        book.setAvailable(false);
        System.out.println(member + " borrowed " + bookTitle);
        notificationService.send(member + " borrowed " + bookTitle);
        storage.save();
    }

    public void returnBook(String member, String bookTitle) {
        T03B_Book book = bookRepository.findByTitle(bookTitle);
        if (book != null) book.setAvailable(true);
        System.out.println(member + " returned " + bookTitle);
    }

    public double calculateFine(int lateDays) {
        return fineCalculator.calculate(lateDays);
    }
}

class T03B_LibraryFacade implements T03B_BookManagement, T03B_MemberManagement {
    private T03B_BookRepository bookRepository;
    private T03B_MemberRepository memberRepository;
    private T03B_BorrowingService borrowingService;
    private T03B_NotificationService notificationService;
    private T03B_LibraryStorage storage;

    public T03B_LibraryFacade(
            T03B_BookRepository bookRepository,
            T03B_MemberRepository memberRepository,
            T03B_BorrowingService borrowingService,
            T03B_NotificationService notificationService,
            T03B_LibraryStorage storage) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowingService = borrowingService;
        this.notificationService = notificationService;
        this.storage = storage;
    }

    public void addBook(String title, String author) {
        bookRepository.add(new T03B_Book(title, author));
        System.out.println("Book added: " + title);
    }

    public void registerMember(String name, String type) {
        T03B_LibraryMember member;
        if ("student".equalsIgnoreCase(type)) member = new T03B_StudentMember(name);
        else if ("guest".equalsIgnoreCase(type)) member = new T03B_GuestMember(name);
        else member = new T03B_RegularMember(name);
        memberRepository.add(member);
        System.out.println("Member registered: " + name);
    }

    public void borrowBook(String member, String book) {
        T03B_LibraryMember m = memberRepository.findByName(member);
        if (m != null && !m.canBorrow()) {
            System.out.println(member + " cannot borrow books.");
            return;
        }
        borrowingService.borrowBook(member, book);
    }

    public void returnBook(String member, String book) {
        borrowingService.returnBook(member, book);
    }

    public double calculateFine(int lateDays) {
        return borrowingService.calculateFine(lateDays);
    }

    public void sendNotification(String message) {
        notificationService.send(message);
    }

    public void saveData() {
        storage.save();
    }

    public void printReport() {
        System.out.println("----- LIBRARY REPORT -----");
        System.out.println("Books: " + bookRepository.count());
        System.out.println("Members: " + memberRepository.count());
    }
}

// =====================================================================
// LAB 04 — Factory Method
// =====================================================================

interface L04_Delivery {
    void deliver();
}

class L04_BikeDelivery implements L04_Delivery {
    public void deliver() { System.out.println("Order delivered by bike."); }
}

class L04_CarDelivery implements L04_Delivery {
    public void deliver() { System.out.println("Order delivered by car."); }
}

class L04_DroneDelivery implements L04_Delivery {
    public void deliver() { System.out.println("Order delivered by drone."); }
}

class L04_DeliveryFactory {
    public static L04_Delivery createDelivery(String type) {
        if (type.equalsIgnoreCase("bike")) return new L04_BikeDelivery();
        if (type.equalsIgnoreCase("car")) return new L04_CarDelivery();
        if (type.equalsIgnoreCase("drone")) return new L04_DroneDelivery();
        throw new IllegalArgumentException("Unknown delivery type");
    }
}

// =====================================================================
// LAB 04 — Abstract Factory
// =====================================================================

interface L04_Payment {
    void pay();
}

interface L04_Notification {
    void send();
}

class L04_CreditCardPayment implements L04_Payment {
    public void pay() { System.out.println("Payment made using credit card."); }
}

class L04_CashPaymentAF implements L04_Payment {
    public void pay() { System.out.println("Payment made using cash."); }
}

class L04_EmailNotificationAF implements L04_Notification {
    public void send() { System.out.println("Notification sent through email."); }
}

class L04_SMSNotificationAF implements L04_Notification {
    public void send() { System.out.println("Notification sent through SMS."); }
}

interface L04_DeliveryServiceFactory {
    L04_Payment createPayment();
    L04_Notification createNotification();
}

class L04_OnlineOrderFactory implements L04_DeliveryServiceFactory {
    public L04_Payment createPayment() { return new L04_CreditCardPayment(); }
    public L04_Notification createNotification() { return new L04_EmailNotificationAF(); }
}

class L04_LocalOrderFactory implements L04_DeliveryServiceFactory {
    public L04_Payment createPayment() { return new L04_CashPaymentAF(); }
    public L04_Notification createNotification() { return new L04_SMSNotificationAF(); }
}

// =====================================================================
// LAB 04 — Builder
// =====================================================================

class L04_FoodOrder {
    private String customerName;
    private String restaurant;
    private String address;
    private String paymentMethod;
    private String instructions;

    private L04_FoodOrder(Builder builder) {
        this.customerName = builder.customerName;
        this.restaurant = builder.restaurant;
        this.address = builder.address;
        this.paymentMethod = builder.paymentMethod;
        this.instructions = builder.instructions;
    }

    public void displayOrder() {
        System.out.println("Customer: " + customerName);
        System.out.println("Restaurant: " + restaurant);
        System.out.println("Address: " + address);
        System.out.println("Payment: " + paymentMethod);
        System.out.println("Instructions: " + instructions);
    }

    static class Builder {
        private String customerName;
        private String restaurant;
        private String address;
        private String paymentMethod;
        private String instructions;

        public Builder(String customerName, String restaurant) {
            this.customerName = customerName;
            this.restaurant = restaurant;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setInstructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public L04_FoodOrder build() {
            return new L04_FoodOrder(this);
        }
    }
}

// =====================================================================
// LAB 04 — Prototype
// =====================================================================

class L04_MenuItem implements Cloneable {
    private String name;
    private double price;

    public L04_MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price) { this.price = price; }

    public void display() {
        System.out.println(name + " - $" + price);
    }

    @Override
    public L04_MenuItem clone() {
        try {
            return (L04_MenuItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

// =====================================================================
// LAB 04 — Singleton
// =====================================================================

class L04_AppConfig {
    private static L04_AppConfig instance;

    private L04_AppConfig() {}

    public static L04_AppConfig getInstance() {
        if (instance == null) instance = new L04_AppConfig();
        return instance;
    }

    public void displayMessage() {
        System.out.println("Application configuration loaded.");
    }
}

// =====================================================================
// LAB 05 — Adapter
// =====================================================================

interface L05_PaymentProcessor {
    void pay(double amount);
}

class L05_ThirdPartyPayment {
    public void makePayment(double amount) {
        System.out.println("Payment of $" + amount + " processed.");
    }
}

class L05_PaymentAdapter implements L05_PaymentProcessor {
    private L05_ThirdPartyPayment paymentService;

    public L05_PaymentAdapter(L05_ThirdPartyPayment paymentService) {
        this.paymentService = paymentService;
    }

    public void pay(double amount) {
        paymentService.makePayment(amount);
    }
}

class L05_MobileWallet {
    public void sendMoney(double amount) {
        System.out.println("Money sent: $" + amount);
    }
}

class L05_MobileWalletAdapter implements L05_PaymentProcessor {
    private L05_MobileWallet wallet;

    public L05_MobileWalletAdapter(L05_MobileWallet wallet) {
        this.wallet = wallet;
    }

    public void pay(double amount) {
        wallet.sendMoney(amount);
    }
}

// =====================================================================
// LAB 05 — Decorator
// =====================================================================

interface L05_Food {
    String getDescription();
    double getPrice();
}

class L05_Burger implements L05_Food {
    public String getDescription() { return "Burger"; }
    public double getPrice() { return 500; }
}

abstract class L05_FoodDecorator implements L05_Food {
    protected L05_Food food;
    public L05_FoodDecorator(L05_Food food) { this.food = food; }
}

class L05_CheeseDecorator extends L05_FoodDecorator {
    public L05_CheeseDecorator(L05_Food food) { super(food); }
    public String getDescription() { return food.getDescription() + " + Cheese"; }
    public double getPrice() { return food.getPrice() + 100; }
}

class L05_SauceDecorator extends L05_FoodDecorator {
    public L05_SauceDecorator(L05_Food food) { super(food); }
    public String getDescription() { return food.getDescription() + " + Extra Sauce"; }
    public double getPrice() { return food.getPrice() + 50; }
}

class L05_FriesDecorator extends L05_FoodDecorator {
    public L05_FriesDecorator(L05_Food food) { super(food); }
    public String getDescription() { return food.getDescription() + " + Fries"; }
    public double getPrice() { return food.getPrice() + 150; }
}

class L05_DrinkDecorator extends L05_FoodDecorator {
    public L05_DrinkDecorator(L05_Food food) { super(food); }
    public String getDescription() { return food.getDescription() + " + Drink"; }
    public double getPrice() { return food.getPrice() + 100; }
}

// =====================================================================
// LAB 05 — Facade
// =====================================================================

class L05_Restaurant {
    public void prepareFood() { System.out.println("Food is being prepared."); }
}

class L05_PaymentService {
    public void processPayment() { System.out.println("Payment processed."); }
}

class L05_DeliveryArrangeService {
    public void arrangeDelivery() { System.out.println("Delivery arranged."); }
}

class L05_NotificationService {
    public void sendConfirmation() { System.out.println("Order confirmation sent."); }
}

class L05_TrackingService {
    public void startTracking() { System.out.println("Delivery tracking started."); }
}

class L05_FoodDeliveryFacade {
    private L05_Restaurant restaurant = new L05_Restaurant();
    private L05_PaymentService payment = new L05_PaymentService();
    private L05_DeliveryArrangeService delivery = new L05_DeliveryArrangeService();
    private L05_NotificationService notification = new L05_NotificationService();
    private L05_TrackingService tracking = new L05_TrackingService();

    public void placeOrder() {
        restaurant.prepareFood();
        payment.processPayment();
        delivery.arrangeDelivery();
        notification.sendConfirmation();
        tracking.startTracking();
    }
}

// =====================================================================
// LAB 05 — Composite
// =====================================================================

interface L05_MenuComponent {
    void show();
}

class L05_FoodItem implements L05_MenuComponent {
    private String name;
    public L05_FoodItem(String name) { this.name = name; }
    public void show() { System.out.println("- " + name); }
}

class L05_MenuCategory implements L05_MenuComponent {
    private String name;
    private List<L05_MenuComponent> items = new ArrayList<>();

    public L05_MenuCategory(String name) { this.name = name; }

    public void add(L05_MenuComponent item) { items.add(item); }

    public void show() {
        System.out.println(name);
        for (L05_MenuComponent item : items) item.show();
    }
}

// =====================================================================
// LAB 05 — Proxy
// =====================================================================

interface L05_DeliveryService {
    void trackOrder();
}

class L05_RealDeliveryService implements L05_DeliveryService {
    public void trackOrder() { System.out.println("Tracking delivery..."); }
}

class L05_DeliveryProxy implements L05_DeliveryService {
    private L05_RealDeliveryService realService;
    private boolean authorized;

    public L05_DeliveryProxy(boolean authorized) {
        this.authorized = authorized;
    }

    public void trackOrder() {
        if (!authorized) {
            System.out.println("Access denied.");
            return;
        }
        if (realService == null) {
            realService = new L05_RealDeliveryService();
        }
        realService.trackOrder();
    }
}

// =====================================================================
// LAB 06 — Strategy
// =====================================================================

interface L06_DeliveryStrategy {
    double calculateFee(double distance);
}

class L06_StandardDelivery implements L06_DeliveryStrategy {
    public double calculateFee(double distance) { return distance * 20; }
}

class L06_ExpressDelivery implements L06_DeliveryStrategy {
    public double calculateFee(double distance) { return distance * 40 + 100; }
}

class L06_PremiumDelivery implements L06_DeliveryStrategy {
    public double calculateFee(double distance) { return 150; }
}

class L06_DeliveryService {
    private L06_DeliveryStrategy strategy;

    public void setStrategy(L06_DeliveryStrategy strategy) {
        this.strategy = strategy;
    }

    public double getFee(double distance) {
        return strategy.calculateFee(distance);
    }
}

// =====================================================================
// LAB 06 — Observer
// =====================================================================

interface L06_OrderObserver {
    void update(String status);
}

class L06_Customer implements L06_OrderObserver {
    public void update(String status) {
        System.out.println("Customer notified: " + status);
    }
}

class L06_DeliveryRider implements L06_OrderObserver {
    public void update(String status) {
        System.out.println("Rider notified: " + status);
    }
}

class L06_RestaurantManager implements L06_OrderObserver {
    public void update(String status) {
        System.out.println("Restaurant manager notified: " + status);
    }
}

class L06_Order {
    private List<L06_OrderObserver> observers = new ArrayList<>();

    public void addObserver(L06_OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(L06_OrderObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        System.out.println("Order status: " + status);
        for (L06_OrderObserver observer : observers) {
            observer.update(status);
        }
    }
}

// =====================================================================
// LAB 06 — Command
// =====================================================================

interface L06_Command {
    void execute();
}

class L06_FoodOrder {
    public void accept() { System.out.println("Order accepted."); }
    public void cancel() { System.out.println("Order cancelled."); }
    public void prepare() { System.out.println("Order preparation started."); }
}

class L06_AcceptOrderCommand implements L06_Command {
    private L06_FoodOrder order;
    public L06_AcceptOrderCommand(L06_FoodOrder order) { this.order = order; }
    public void execute() { order.accept(); }
}

class L06_CancelOrderCommand implements L06_Command {
    private L06_FoodOrder order;
    public L06_CancelOrderCommand(L06_FoodOrder order) { this.order = order; }
    public void execute() { order.cancel(); }
}

class L06_PrepareOrderCommand implements L06_Command {
    private L06_FoodOrder order;
    public L06_PrepareOrderCommand(L06_FoodOrder order) { this.order = order; }
    public void execute() { order.prepare(); }
}

class L06_RestaurantController {
    public void executeCommand(L06_Command command) {
        command.execute();
    }
}

// =====================================================================
// LAB 06 — Template Method
// =====================================================================

abstract class L06_PaymentProcessor {
    public final void processPayment() {
        validateOrder();
        makePayment();
        generateReceipt();
    }

    private void validateOrder() {
        System.out.println("Order validated.");
    }

    protected abstract void makePayment();

    private void generateReceipt() {
        System.out.println("Receipt generated.");
    }
}

class L06_CashPayment extends L06_PaymentProcessor {
    protected void makePayment() {
        System.out.println("Payment received in cash.");
    }
}

class L06_CardPayment extends L06_PaymentProcessor {
    protected void makePayment() {
        System.out.println("Payment processed by card.");
    }
}

class L06_WalletPayment extends L06_PaymentProcessor {
    protected void makePayment() {
        System.out.println("Payment processed by wallet.");
    }
}

// =====================================================================
// LAB 06 — State
// =====================================================================

interface L06_OrderState {
    void next(L06_OrderContext order);
    String getStatus();
}

class L06_PlacedState implements L06_OrderState {
    public void next(L06_OrderContext order) {
        order.setState(new L06_PreparingState());
    }
    public String getStatus() { return "Order placed"; }
}

class L06_PreparingState implements L06_OrderState {
    public void next(L06_OrderContext order) {
        order.setState(new L06_ReadyForPickupState());
    }
    public String getStatus() { return "Order preparing"; }
}

class L06_ReadyForPickupState implements L06_OrderState {
    public void next(L06_OrderContext order) {
        order.setState(new L06_DeliveredState());
    }
    public String getStatus() { return "Ready for pickup"; }
}

class L06_DeliveredState implements L06_OrderState {
    public void next(L06_OrderContext order) {
        System.out.println("Order is already delivered.");
    }
    public String getStatus() { return "Order delivered"; }
}

class L06_OrderContext {
    private L06_OrderState state = new L06_PlacedState();

    public void setState(L06_OrderState state) { this.state = state; }

    public void nextState() { state.next(this); }

    public void showStatus() { System.out.println(state.getStatus()); }
}

// =====================================================================
// LAB 06 — Iterator
// =====================================================================

class L06_MenuItem {
    private String name;
    public L06_MenuItem(String name) { this.name = name; }
    public String getName() { return name; }
}

class L06_RestaurantMenu implements Iterable<L06_MenuItem> {
    private List<L06_MenuItem> items = new LinkedList<>();

    public void addItem(L06_MenuItem item) { items.add(item); }

    public Iterator<L06_MenuItem> iterator() {
        return items.iterator();
    }
}

// =====================================================================
// LAB 06 — Decoupling
// =====================================================================

class L06_DeliveryProcessor {
    public void processDelivery(L06_DeliveryStrategy strategy, double distance) {
        double fee = strategy.calculateFee(distance);
        System.out.println("Delivery distance: " + distance);
        System.out.println("Delivery fee: " + fee);
    }
}
