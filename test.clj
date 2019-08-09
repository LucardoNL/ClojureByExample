(defn greeting [greet]
    (str greet " World!"))

(greeting "Hello")

(+ 4 2)

(print "Enter an operation: ")
(flush)
; (read-line)


;----------------------------------------------------
; Very basic calculator with input validation. 
; Function calls below the calc fn replicate stdin.
(defn calc [oper inOne inTwo]
    {:pre [(number? inOne)(number? inTwo)]}
    ((case 
        oper "+" + "-" - "/" / "*" *
        list
    ) inOne inTwo)
)

(calc "+" 5 5) ; Good case
(calc "p" 2 4) ; Bad operator
(calc + 1 "p") 
(calc + 1 2) ; Does not immitate stdin
(calc "^" 2 6)
;----------------------------------------------------