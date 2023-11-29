


-- 1. Soma de múltiplos de 3 ou 5, não múltiplos de 2, menores que 10,000
somaMultiplos :: Integer
somaMultiplos = sum [x | x <- [1..9999], x `mod` 3 == 0 || x `mod` 5 == 0, x `mod` 2 /= 0]

-- 2. Diferença entre a soma de Fibonacci ímpares e pares menores que 100,000

fibonacci :: Integer -> [Integer]
fibonacci n = takeWhile (<n) fibs
  where
    fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

somaDiferenca :: Integer -> Integer
somaDiferenca n = abs $ somaImpares - somaPares
  where
    fibs = fibonacci n
    somaPares = sum [x | x <- fibs, even x]
    somaImpares = sum [x | x <- fibs, odd x]
 


-- 3. Fatores primos de um número entre 100,000,000 e 1,000,000,000
fatoresPrimos :: Integer -> [Integer]
fatoresPrimos n = go n 2 []
    where
        go 1 _ factors = factors
        go m d factors
            | m `mod` d == 0 = go (m `div` d) d (d : factors)
            | otherwise = go m (d + 1) factors

-- 4. Diferença entre a soma dos quadrados e o quadrado da soma de inteiros em uma lista
diferencaSomaQuadrados :: [Integer] -> Integer
diferencaSomaQuadrados xs = (sum [x^2 | x <- xs]) - (sum xs)^2

-- 5. Crivo de Euler para encontrar números primos menores que n
crivoEuler :: Integer -> [Integer]
crivoEuler n = sieve [2..n]
    where
        sieve [] = []
        sieve (p:xs) = p : sieve [x | x <- xs, x `mod` p /= 0]

-- 6. Lista infinita de inteiros que dobram a cada passo
listaInfinitaDobra :: Integer -> [Integer]
listaInfinitaDobra n = iterate (* 2) n

-- 7. Trocar vogais em uma string
trocarVogais :: String -> String
trocarVogais str = map (\c -> case c of
    'a' -> 'u'
    'e' -> 'o'
    'i' -> 'i'
    'o' -> 'e'
    'u' -> 'a'
    _   -> c) str

-- 8. Números da sequência de Lucas menores que n
sequenciaLucas :: Integer -> [Integer]
sequenciaLucas n = takeWhile (<= n) (2:1:zipWith (+) (sequenciaLucas n) (tail (sequenciaLucas n)))

-- 9. Função análoga a map para duas listas
map2 :: (a -> b -> c) -> [a] -> [b] -> [c]
map2 _ [] _ = []
map2 _ _ [] = []
map2 f (x:xs) (y:ys) = f x y : map2 f xs ys

main :: IO ()
main = do

    -- Teste das funções
    putStrLn "1. Soma de múltiplos de 3 ou 5, não múltiplos de 2, menores que 10,000"
    print somaMultiplos
    putStrLn ""

    putStrLn "2. Diferença entre a soma de Fibonacci ímpares e pares menores que 100,000"
    print $ somaDiferenca 999
    putStrLn ""

    putStrLn "3. Fatores primos de um número entre 100,000,000 e 1,000,000,000"
    print (fatoresPrimos 123456789)
    putStrLn ""

    putStrLn "4. Diferença entre a soma dos quadrados e o quadrado da soma de inteiros em uma lista"
    print (diferencaSomaQuadrados [1..10])
    putStrLn ""

    putStrLn "5. Crivo de Euler para encontrar números primos menores que n"
    print (crivoEuler 100)
    putStrLn ""

    putStrLn "6. Lista infinita de inteiros que dobram a cada passo"
    print (take 10 (listaInfinitaDobra 1))
    putStrLn ""

    putStrLn "7. Trocar vogais em uma string"
    print (trocarVogais "aeiou")
    putStrLn ""

    putStrLn "8. Números da sequência de Lucas menores que n"
    print (sequenciaLucas 100)
    putStrLn ""

    putStrLn "9. Função análoga a map para duas listas"
    print (map2 (+) [1,2,3] [10,11,12])


