import java.util.Scanner;

class Customer {
    String name;
    int age;
    boolean membership_status;
    String id;

    void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name:");
        name = sc.nextLine();

        System.out.print("Enter your age:");
        age = sc.nextInt();

        System.out.print("Enter your membership status:");
        membership_status = sc.nextBoolean();

        boolean b = true;
        while (b) {
            System.out.print("Enter your ID:");
            id = sc.next();
            if (id.length() == 4) {
                System.out.println("Your ID is valid");
                b = false;
            } else {
                System.out.println("Id is invalid. Please try again");
            }
        }
    }

    void update(Customer arr[], String a) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].id.equals(a)) {
                boolean up = false;
                while (!up) {
                    System.out.println("1.Update Name");
                    System.out.println("2.Update Age");
                    System.out.println("3.Update Membership");
                    System.out.println("4.Update ID");
                    System.out.println("5.Exit");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1 -> {
                            sc.nextLine();
                            arr[i].name = sc.nextLine();
                        }
                        case 2 -> arr[i].age = sc.nextInt();
                        case 3 -> arr[i].membership_status = sc.nextBoolean();
                        case 4 -> {
                            boolean b = true;
                            while (b) {
                                arr[i].id = sc.next();
                                if (arr[i].id.length() == 4) b = false;
                            }
                        }
                        case 5 -> up = true;
                    }
                }
                return;
            }
        }
        System.out.println("ID not found");
    }
}

class Computer {
    int ComputerNumber;
    boolean isAvailable;
    static double HourlyRate = 100;

    Computer(int ComputerNumber, double HourlyRate) {
        this.ComputerNumber = ComputerNumber;
        this.isAvailable = true;
    }

    void markOccupied() {
        isAvailable = false;
    }

    void releaseComputer() {
        isAvailable = true;
    }

    void ComputerDetails() {
        System.out.println("Computer Number: " + ComputerNumber);
        System.out.println("Status: " + (isAvailable ? "Available" : "Occupied"));
    }
}

class Session {
    int SessionID;
    Customer cust;
    Computer comp;
    long start_time;
    long end_time;
    boolean isActive;
    double duration;
    double Total_Cost;

    void startSession(int sessionID, Computer comp, long s, long e, boolean a, Customer cust) {
        this.SessionID = sessionID;
        this.comp = comp;
        this.cust = cust;
        this.start_time = System.currentTimeMillis();
        this.isActive = true;
        comp.markOccupied();
    }

    void endSession() {
        end_time = System.currentTimeMillis();
        isActive = false;
        comp.releaseComputer();
    }

    void calcuateDuration() {
        duration = (end_time - start_time) / (1000.0 * 60 * 60);
    }

    void calculateCharges() {
        Total_Cost = duration * Computer.HourlyRate;
    }

    void displaySessionDetails() {
        System.out.println("Session ID: " + SessionID);
        System.out.println("Customer ID: " + cust.id);
        System.out.println("Computer: " + comp.ComputerNumber);
        System.out.println("Hours: " + duration);
        System.out.println("Cost: " + Total_Cost);
    }
}

class CyberCafe {
    Customer[] customers;
    Computer[] computers;
    Session[] sessions;
    int session_count = 0;

    CyberCafe(Customer[] customers, Computer[] computers, Session[] sessions) {
        this.customers = customers;
        this.computers = computers;
        this.sessions = sessions;
    }

    void startNewSession(int SessionId, String customerId, int computerNumber) {
        Customer selectedCustomer = null;
        Computer selectedComputer = null;

        for (Customer c : customers)
            if (c != null && c.id.equals(customerId)) selectedCustomer = c;

        for (Computer c : computers)
            if (c.ComputerNumber == computerNumber && c.isAvailable) selectedComputer = c;

        if (selectedCustomer == null || selectedComputer == null) return;

        sessions[session_count] = new Session();
        sessions[session_count].startSession(SessionId, selectedComputer, 0, 0, true, selectedCustomer);
        session_count++;
    }

    void endSession(int sessionId) {
        for (int i = 0; i < session_count; i++) {
            if (sessions[i].SessionID == sessionId && sessions[i].isActive) {
                sessions[i].endSession();
                sessions[i].calcuateDuration();
                sessions[i].calculateCharges();
                sessions[i].displaySessionDetails();
                return;
            }
        }
        System.out.println("Active session not found");
    }

    void displayAvailableComputer() {
        for (Computer c : computers)
            if (c.isAvailable) c.ComputerDetails();
    }

    void generateUsageReport() {
        for (int i = 0; i < session_count; i++)
            sessions[i].displaySessionDetails();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Customer[] cus = new Customer[5];
        for (int i = 0; i < cus.length; i++) cus[i] = new Customer();

        Computer[] comps = {
                new Computer(1, 100),
                new Computer(2, 100)
        };

        Session[] sess = new Session[10];

        CyberCafe cafe = new CyberCafe(cus, comps, sess);

        boolean run = true;
        while (run) {
            System.out.println("1.Start Session 2.End Session 3.Available PCs 4.Report 5.Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1 -> cafe.startNewSession(sc.nextInt(), sc.next(), sc.nextInt());
                case 2 -> cafe.endSession(sc.nextInt());
                case 3 -> cafe.displayAvailableComputer();
                case 4 -> cafe.generateUsageReport();
                case 5 -> run = false;
            }
        }
    }
}
