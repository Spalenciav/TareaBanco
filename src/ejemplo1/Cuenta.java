package ejemplo1;

/*
Autor: Santiago Palencia
Tema: Codigo de banco 
*/

public class Cuenta {
    private String nombreDelTitular;
    private int saldoDisponible;
    private static final double Cuatroxmil = 0.004;  //Impuesto de 4xmil

    public Cuenta(Cliente cliente) {        //se crea una cuenta y se le pone valor de 0
        this.nombreDelTitular = cliente.getNombre();
        this.saldoDisponible = 0;
    }

    private int calcularCuatroPorMil(int cantidad) {    //Funcion que calcula el impuesto 4xmil de una cantidad 
        return (int) (cantidad * Cuatroxmil);
    }

    public boolean consignar(int cantidad, boolean aplicarImpuesto) {
        if (cantidad > 0) {                  //El valor de la consignacion debe ser mayor a 0
            int impuesto = aplicarImpuesto ? calcularCuatroPorMil(cantidad) : 0; // pregunta si se aplica el impuesto 4xmil, si es true se aplica, si es false no se aplica para el caso de transferencias, si es true se le asigna el valor del impuesto a la variable
            saldoDisponible += (cantidad - impuesto); // el valor puede ser el valor del 4xmil del impuesto en caso true o 0 si es false el paso anterior
            return true;
        }
        return false;               // Retorna false si la cantidad es invalida
    }

    public boolean retirar(int cantidad) {
        if (cantidad > 0 && cantidad <= saldoDisponible) {
            saldoDisponible -= cantidad;
            return true;
        }
        return false;          // Retorna false si la cantidad no es valida o es insuficiente 
    }

    public boolean transferir(Cuenta cuentaDestino, int cantidad) {
        if (cantidad > 0) {             
            int impuesto = calcularCuatroPorMil(cantidad);
            if (retirar(cantidad + impuesto)) {      // se verifica que el saldo pueda suplir la transferencia y se retira de la cuenta
                cuentaDestino.consignar(cantidad, false);   // Consignar solo la cantidad sin el impuesto
                return true;
            }
        }
        return false;          // Retorna false si la transferencia no se puede realizar
    }

    public int getSaldo() {
        return saldoDisponible;
    }
    
    
    public String getNombreDelTitular() {
        return nombreDelTitular;
    }
}