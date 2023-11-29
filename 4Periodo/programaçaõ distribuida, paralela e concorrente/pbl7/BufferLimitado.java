

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLimitado {

    private int numeroProduto;
    private int valorMaximo;

    final Lock mutex = new ReentrantLock();
    final Condition notFull = mutex.newCondition();
    final Condition notEmpty = mutex.newCondition();
    final Condition notDepositing = mutex.newCondition();
    final Condition notRemoving = mutex.newCondition();
    private boolean Depositando;
    private boolean Remocao;

    public BufferLimitado(int valorMaximo){
        this.valorMaximo = valorMaximo;
        numeroProduto = 0;
        this.Depositando = false;
        this.Remocao = false;
    }
    public synchronized void inserir(int prod, String Nome) throws InterruptedException{
        mutex.lock();
        try {
            while (Depositando)
                notDepositing.await();
            Depositando = true;
            while (prod > 0) {
                while (prod == numeroProduto)
                    notFull.await();
                while (numeroProduto == valorMaximo) wait();
                numeroProduto++;
                prod--;
                notEmpty.signal();
            }
        }finally {
            Depositando = false;
            notDepositing.signal();
            mutex.unlock();
        }
    }
    public synchronized void remover(int prod, String nomeProduto) throws InterruptedException{
        mutex.lock();
        try {
            while (Remocao)
                notRemoving.await();
            Remocao = true;
            while (prod > 0) {
                while (numeroProduto == 0)
                    notEmpty.await();
                numeroProduto--;
                prod--;
                notFull.signal();
            }
        }finally {
            Remocao = false;
            notRemoving.signal();
            mutex.unlock();
        }
    }
}

