import Pyro5.api
import threading
import math
from collections import Counter

@Pyro5.api.expose
class Voter:
    def __init__(self, ve, to):
        self.ve = ve  
        self.to = to  
        self.messages = []
        self.lock = threading.Lock()
        self.timer = None

    def start_timer(self):
        self.timer = threading.Timer(self.to, self.handle_timeout)
        self.timer.start()

    def handle_timeout(self):
        with self.lock:
            self.produce_verdict(timeout_triggered=True)

    def send(self, msg):
        with self.lock:
            self.messages.append(msg)
            if len(self.messages) == 1:
                self.start_timer()
            if len(self.messages) == self.ve:
                if self.timer:
                    self.timer.cancel()  
                self.produce_verdict()

    def produce_verdict(self, timeout_triggered=False):
        if timeout_triggered and not self.messages:
            print("Nenhuma mensagem recebida")
            return

        count = Counter(self.messages)
        majority_count = math.ceil((len(self.messages) + 1) / 2)
        for msg, cnt in count.items():
            if cnt >= majority_count:
                print(f"Veredito: {msg}")
                return
        print("Nao houve maioria")


if __name__ == "__main__":
    # Alterando os valores de ve e to
    voter = Voter(ve=3, to=5)  # Altere os valores conforme necessário
    daemon = Pyro5.server.Daemon()
    uri = daemon.register(voter)
    print(f"URI do voter: {uri}")
    daemon.requestLoop()
