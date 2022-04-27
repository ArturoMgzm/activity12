import java.util.HashMap;
import java.io.*;

public class actividad12{

    public static class AddressBook{

        private HashMap<String,String> contacts = new HashMap<String, String>();

        //constructor, cuando se construye el objeto carga lo que sabe del archivo
        public AddressBook(){
            this.load();
        }

        //load
        //abre un archivo y lee todos los datos en el archivo
        private void load(){
            String inputFilename = "./contacts.txt";
            //con un BufferedReader se lee de linea en linea
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(inputFilename));
                String line;
                //mientras aun haya lineas
                while ((line = bufferedReader.readLine()) != null) {
                    //el substring antes de la coma es el numero, despues de la coma es el nombre
                    this.contacts.put(line.substring(0,line.indexOf(",")),line.substring(line.indexOf(",")+1));
                }
            } catch(IOException e) {
                System.out.println("IOException catched while reading: " + e.getMessage());
            } finally {
                try {
                    //cierra el archivo
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    System.out.println("IOException catched while closing: " + e.getMessage());
                }
            }
        }

        //save
        //actualiza el archivo de contactos
        private void save(){
            String outputFilename = "./contacts.txt";
            BufferedWriter bufferedWriter = null;
            //utiliza bufferedwriter para escibir por renglon
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(outputFilename));
                String line;
                //por cada elemento del HashMap los escribe con una coma en medio
                for (String i : this.contacts.keySet()) {
                    bufferedWriter.write(i+","+this.contacts.get(i)+"\n");
                }
            } catch(IOException e) {
                System.out.println("IOException catched while reading: " + e.getMessage());
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    System.out.println("IOException catched while closing: " + e.getMessage());
                }
            }
        }

        //list
        //imprime cada elemento del hashmap con el formato solicitado
        private void list(){
            System.out.println("Contactos:");
            for (String i : this.contacts.keySet()) {
                System.out.println(i + ":" + this.contacts.get(i));
            }
        }

        //create
        //solicita un numero y un nombre y lo guarda en los contactos
        private void create(){
            String number;
            String name;
            try {
                //pide al usuario que inserte el numeor y el nombre del usuario
                System.out.println("Escribe el numero telefonico del contacto:");
                InputStreamReader streamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                number = bufferedReader.readLine();
                System.out.println("Escribe el nombre del contacto:");
                name = bufferedReader.readLine();
                //los agrega a los contactos
                this.contacts.put(number,name);
                //actualiza el archivo
                this.save();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //delete
        //elimina un numero especificado de tus contactos
        private void delete(){
            String number;
            System.out.println("Escribe el numero telefonico del contacto a eliminar:");
            try {
                //soliticta el numero
                InputStreamReader streamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                number = bufferedReader.readLine();
                //elimina el numero
                this.contacts.remove(number);
                //actualiza el archivo
                this.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //menu
        //permite al usuario interactuar con el sistema
        public void menu(){
            String opcion = "";
            boolean exit = false;
            //hace loops hasta que el usuario desee salir
            while(!exit){
                System.out.println("Escribe el numero de la opcion que deseas realizar:");
                System.out.println("1.- Ver listado de contactos");
                System.out.println("2.- Agregar nuevo contacto");
                System.out.println("3.- Eliminar Contacto");
                System.out.println("4.- Salir");
                //pregunta la opcion
                try {
                    InputStreamReader streamReader = new InputStreamReader(System.in);
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    opcion = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //switch con los metodos que mandaria a llamar
                switch(opcion){
                    case "1":
                        this.list();
                        break;
                    case "2":
                        this.create();
                        break;
                    case "3":
                        this.delete();
                        break;
                    case "4":
                        exit = true;
                        break;
                    default:
                        System.out.println("Opcion no reconocida, vuelvalo a intentar");
                        break;
                }

            }
        }
    }

    //main
    //inicialzia un objeto de la clase AddressBook y manda a llamar el menu.
    public static void main(String[] args) {
        AddressBook misContactos = new AddressBook();
        misContactos.menu();
    }
}
