import random

class Util:
    letras = "123456789"
    tamanho = len(letras)
    
    @staticmethod
    def gerar_palavra(n):     
        palavra = ''

        for i in range(n):
            palavra += Util.letras[random.randrange(Util.tamanho)]
        
        return palavra
        
