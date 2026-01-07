
import java.util.Scanner;

class Main
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Number of customers:");
        int n=sc.nextInt();
        Customer cus[]=new Customer[n];
        for(int i=0;i<n;i++)
        {
            cus[i] = new Customer();
        }
        int count=0;
        boolean b=true;
        do
        {

            System.out.println("1.Register new Customer\n2.Update Customer Information\n3.Manage Customer Account\n4.Exit");
            System.out.print("Enter choice:");
            int x=sc.nextInt();

            switch(x)
            {       case 1->
            {

                if(count<n) {
                    cus[count].register();
                    count++;
                }
                else
                {
                    System.out.println("Reached Customer limit");
                }
                break;
            }
                case 2->
                {
                    System.out.print("Enter Customer ID:");
                    String id=sc.next();
                    cus[0].update(cus,id);
                    break;

                }
                case 3->
                {
                    System.out.print("Enter customer id:");
                    String  id=sc.next();
                    cus[0].manage(cus,id);
                    break;
                }
                case 4->
                {  b=false;
                    break;

                }


            }

        }while(b);
    }
}

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
                System.out.print("Your ID is valid");
                b = false;

            } else {
                System.out.print("Id is invalid.Please try again");


            }
        }

    }

    void update(Customer arr[], String a) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].id.equals(a)) {
                boolean up = false;
                while (!up) {
                    System.out.println("1.Update Customer name");
                    System.out.println("2.Update Customer age");
                    System.out.println("3.Update Customer membership status");
                    System.out.println("4.Update Customer ID");
                    System.out.println("5.Exit");
                    System.out.println();
                    System.out.print("Enter your choice:");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter Updated Name:");
                            sc.nextLine();
                            name = sc.nextLine();
                            System.out.println("Updated Name: " + name);
                        }
                        case 2 -> {
                            System.out.print("Enter Updated Age:");
                            age = sc.nextInt();
                        }
                        case 3 -> {
                            System.out.print("Enter updated membership status:");
                            membership_status = sc.nextBoolean();
                        }
                        case 4 -> {
                            boolean b = true;
                            while (b) {
                                System.out.println("Enter updated ID:");
                                id = sc.next();
                                if (id.length() == 4) {
                                    System.out.println("Your ID is valid");
                                    b = false;

                                } else {
                                    System.out.print("Id is invalid.Please try again");

                                }
                            }
                        }
                        case 5 -> {
                            System.out.println("Exiting...");
                            up = true;
                        }
                    }
                }
            }
            else
            {
                System.out.println("ID not found");

            }
        }

    }

    void manage(Customer[] brr, String s) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < brr.length; i++) {
            if (brr[i].id.equals(s)) {
                boolean down = false;
                while (!down) {
                    System.out.println("1.Display Customer Information");
                    System.out.println("2.Update Customer Information");
                    System.out.println("3.Exit");

                    System.out.print("Enter your choice:");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println(brr[i].name);
                            System.out.println(brr[i].age);
                            System.out.println(brr[i].id);
                            System.out.println(brr[i].membership_status);
                        }
                        case 2 -> {
                            update(brr, s);
                        }
                        case 3 -> {
                            down = true;
                        }
                    }


                }
            }
        }
    }
}
