; Basic example of a Clojure form / expression: A line of code (....)
(println "Hello, world!")

; Variables are called symbols in Clojure. Below will show data type of a: Symbol/
; ' will prevent a from from being evaluated
(type 'a)

; Clojure is a Lisp, and will evaluate everything as lists, evaluating the contents of a list left to right.
; Binding (def) a value ("aaaaa") to a symbol (a) within a scope (default: user) and printing it.
(def a "aaaaa")

(println a)

; Printing a Symbol with no value results in an error.
(println b)

; A Lisp will evualate a list of lists left to right.
; Binding a value to a symbol within a private variable scope (let) and printing it (println).
; let takes a vector: [symbol, value]
(let [l "light"] (println (str "God said let there be " l)))

; This will not resolve, as a is referenced before it is bound a value. 
((println (str "Right to left? " a) let [a "Yes"]))