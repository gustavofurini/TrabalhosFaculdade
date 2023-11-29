from sys import displayhook
import chess

Tabuleiro = chess.Board()

# VALORES DE CADA PEÇA
values = {
        chess.PAWN: 100,
        chess.KNIGHT: 300,
        chess.BISHOP: 300,
        chess.ROOK: 500,
        chess.QUEEN: 900,
        chess.KING: 100000
    }


# AVALIAR O RESULTADO FINAL
def AvaliarResultado():
  if Tabuleiro.is_checkmate():
      if Tabuleiro.turn:
          return -9999
      else:
          return 9999
  if Tabuleiro.is_stalemate():
        return 0
  if Tabuleiro.is_insufficient_material():
        return 0
  
  # NOMEANDO PEÇAS
  peaoBranco = len(Tabuleiro.pieces(chess.PAWN, chess.WHITE))
  peaoPreto = len(Tabuleiro.pieces(chess.PAWN, chess.BLACK))
  cavaloBranco = len(Tabuleiro.pieces(chess.KNIGHT, chess.WHITE))
  cavaloPreto = len(Tabuleiro.pieces(chess.KNIGHT, chess.BLACK))
  bispoBranco = len(Tabuleiro.pieces(chess.BISHOP, chess.WHITE))
  bispoPreto = len(Tabuleiro.pieces(chess.BISHOP, chess.BLACK))
  torreBranca = len(Tabuleiro.pieces(chess.ROOK, chess.WHITE))
  torrePreta = len(Tabuleiro.pieces(chess.ROOK, chess.BLACK))
  rainhaBranca = len(Tabuleiro.pieces(chess.QUEEN, chess.WHITE))
  rainhaPreta = len(Tabuleiro.pieces(chess.QUEEN, chess.BLACK))


  # HEURISTICA BASEADA NA QUANTIDADE DE PEÇAS E SEUS VALORES
  quantidadeDePecasBrancas = 100 * (peaoBranco) + 300 * (cavaloBranco) + 300 * (bispoBranco) + 500 * (torreBranca) + 900 * (rainhaBranca)
  quantidadeDePecasPretas = 100 * (peaoPreto) + 300 * (cavaloPreto) + 300 * (bispoPreto) + 500 * (torrePreta) + 900 * (rainhaPreta)

  # HEURISTICA BASEADO NA POSIÇÃO DOS PEÕES  
  pontuacaoBranco = 0
  for square in chess.SQUARES:
        piece = Tabuleiro.piece_at(square)
        if piece is not None and piece.color == peaoBranco:
            # DIVDE A CASA NO TABULEIRO POR 8, E PEGA O NÚMERO DA FILEIRA
            # GANHA UM VALOR ADICIONAL DE 100 PONTOS CONFORME VAI AVANÇANDO
            
            if piece.piece_type == chess.PAWN:
                pontuacaoBranco += 100 * (square // 8)  #
            else:
                pontuacaoBranco += values[piece.piece_type]
  pontuacaoPreto = 0
  for square in chess.SQUARES:
        piece = Tabuleiro.piece_at(square)
        if piece is not None and piece.color == peaoPreto:
            # ADICIONA VALOR
            if piece.piece_type == chess.PAWN:
                pontuacaoPreto += 100 * (7 - square // 8)  # PEÕES GANHAM VALOR CONFORME AVANÇAM
            else:
                pontuacaoPreto += values[piece.piece_type] 
  
  # AVALIAÇÃO FINAL
  avalia = quantidadeDePecasBrancas + quantidadeDePecasPretas + pontuacaoBranco - pontuacaoPreto 
  if Tabuleiro.turn:
      return avalia
  else:
      return -avalia

# BUSCA EM PROFUNDIDADE (ALPHA E BETA)
def BuscaEmProfundidade(alpha, beta):
    avaliacao = AvaliarResultado()
    if (avaliacao >= beta):
        return beta
    if (avaliacao > alpha):
        alpha = avaliacao

    for move in Tabuleiro.legal_moves:
        if Tabuleiro.is_capture(move):
            Tabuleiro.push(move)
            pontuacao = -BuscaEmProfundidade(-beta, -alpha)
            Tabuleiro.pop()
            if (pontuacao >= beta):
                return beta
            if (pontuacao > alpha):
                alpha = pontuacao
    return alpha


# FUNÇÃO ALPHA E BETA PARA BYSCAR MELHOR JOGADA E DPS AVALIAR
# E RETORNAR O MELHOR MOVIMENTO
def alphabeta(alpha, beta, profundidade):
    MelhorPontuacao = -9999
    if (profundidade == 0):
        return BuscaEmProfundidade(alpha, beta)
    for movimento in Tabuleiro.legal_moves:
        Tabuleiro.push(movimento)
        pontuacao = -alphabeta(-beta, -alpha, profundidade - 1)
        Tabuleiro.pop()
        if (pontuacao >= beta):
            return pontuacao
        if (pontuacao > MelhorPontuacao):
            MelhorPontuacao = pontuacao
        if (pontuacao > alpha):
            alpha = pontuacao
    return MelhorPontuacao


# AVALIA MELHOR MOVIMENTO PARA O COMPUTADOR BASEADO NO TABULEIRO
def MovimentoComputador(depth):
    MelhorMovimento = chess.Move.null()
    MelhorValor = -99999
    alpha = -100000
    beta = 100000
    for movimento in Tabuleiro.legal_moves:
        Tabuleiro.push(movimento)
        TabuleiroValor = -alphabeta(-beta, -alpha, depth - 1)
        if TabuleiroValor > MelhorValor:
            MelhorValor = TabuleiroValor
            MelhorMovimento = movimento
        if TabuleiroValor > alpha:
            alpha = TabuleiroValor
        Tabuleiro.pop()
    return MelhorMovimento



# RECEBER E VERIFICAR O MOVIMENTO DO JOGADOR
def Jogada():
    movimento = input("DIGITE SUA JOGADA NO FORMATO 'e2e4': ")
    try:
        movimento = chess.Move.from_uci(movimento)
        if movimento in Tabuleiro.legal_moves:
            return movimento
        else:
            print("JOGADA INVÁVILDA.")
            return Jogada()
    except:
        print("JOGADA INVALIDA.")
        return Jogada()


def EscolherCor():
    while True:
        cor = input("ESCOLHA A COR DAS PEÇAS (BRANCAS OU PRETAS): ").lower()
        if cor == "brancas":
            return chess.WHITE
        elif cor == "pretas":
            return chess.BLACK
        else:
            print("ERRO! TENTE NOVAMENTE!.")


def main():
    cor = EscolherCor()

    while not Tabuleiro.is_game_over(claim_draw=True):
        if not Tabuleiro.turn:
            if cor == chess.BLACK:
                movimentoUsuario = Jogada()
                Tabuleiro.push(movimentoUsuario)
            else:
                resultado = MovimentoComputador(3)
                Tabuleiro.push(resultado)
        else:
            if cor == chess.WHITE:
                movimentoUsuario = Jogada()
                Tabuleiro.push(movimentoUsuario)
            else:
                resultado = MovimentoComputador(3)
                Tabuleiro.push(resultado)

        displayhook(Tabuleiro)
        print("\n")


if __name__ == "__main__":
    main()