; Problem 1
(reduce 
    + 
    (filter 
        #(or 
            (zero? (mod % 3)) 
            (zero? (mod % 5))
        )
        (range 1000)
    )  
)
; => 233168

; Problem 2
(reduce 
    + 
    (filter 
        even? 
            (loop [a 0 b 1 c ()] 
                (if (> a 4000000)
                    c
                (recur b (+ a b) (conj c a))
                )
            )
    )  
)
; => 4613732