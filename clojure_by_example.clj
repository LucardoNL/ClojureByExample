; Basic example of a Clojure form / expression: A line of code (....)
(println "Hello, world!")

; Variables are called symbols in Clojure. Below will show data type of a: Symbol/
; ' will prevent a from from being evaluated
(type 'a)

; ============== Bindings ===============
; Clojure is a Lisp, and will evaluate everything as lists, evaluating the contents of a list left to right.
; Binding (def) a value ("aaaaa") to a symbol (a) within a scope (default: user) and printing it.
(def a "aaaaa")
(println a)

; Printing a Symbol with no value results in an error.
(println b)

; A Lisp will evualate a list of lists left to right.
; Binding a value to a symbol within a private variable scope (let) and printing it concated (println (str)).
; let takes a vector: [symbol, value]
(let [l "light"] (println (str "God said let there be " l)))

; These will not resolve, as a is referenced before it is bound a value. 
((println (str "Right to left? " a) let [a "Yes"]))
((println (str "Right to left? " a) (def a "Yes")))

; The symbol a is referenced outside of the let, which means it is not in scope. 
(let [n "No"])
(println (str "Will this resolve? " n))

; let allows multiple bindings, and you can reference previous bindings
(let [l "light"
    l_d (str l " and darkness")]
    println (str "God said " l_d))

; Nesting lets will make the innermost Symbol assignement apply to its own scope
; This is also know as it's lexical (words in a sentence) scope
(let [a "aaa"]
    (let [a "AAA"]
        (println a)) ; Will print "AAA"
    (println a)) ; Will print "aaa"

; Clojure will look inside out (expanding scope) to find the first applying symbol assignement
(let [a "AAA"]
    (let []
        (println a)))

; Note: using Def will scope in namespace (user) instead of in the lexical scope, and should be avoided if you do not want to introduce state.

; ============== Functions ===============

