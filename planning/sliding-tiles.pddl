(define (domain sliding-tile)
  
  (:requirements :strips :action-costs)
  
  (:predicates
   (is-tile ?x)
   (is-col ?x)
   (is-row ?x)
   (tile-at ?t ?r ?c)
   (is-blank ?r ?c)
   (next-row ?r1 ?r2)
   (next-column ?c1 ?c2)
   (is-glued ?t)
  )
  
  (:functions
   (WEIGHT ?t) - number
   (total-cost) - number)
   
  (:action unstick
    :parameters(?tile)
    :precondition (is-glued ?tile)
    :effect (not (is-glued ?tile))
  )

  (:action move-tile-down
    :parameters (?tile ?old-row ?new-row ?col)
    :precondition (and (is-tile ?tile)
    			(not (is-glued ?tile))
    			(is-row ?old-row)
    			(is-row ?new-row)
    			(is-col ?col)
    			(next-row ?old-row ?new-row)
                       (tile-at ?tile ?old-row ?col)
                       (is-blank ?new-row ?col))
    :effect (and (not (tile-at ?tile ?old-row ?col))
                 (not (is-blank ?new-row ?col))
                 (tile-at ?tile ?new-row ?col)
                 (is-blank ?old-row ?col)
                 (increase (total-cost) (WEIGHT ?tile))))

  (:action move-tile-up
    :parameters (?tile ?old-row ?new-row ?col)
    :precondition (and (is-tile ?tile)
    			(not (is-glued ?tile))
    			(is-row ?old-row)
    			(is-row ?new-row)
    			(is-col ?col)
    			(next-row ?new-row ?old-row)
                       (tile-at ?tile ?old-row ?col)
                       (is-blank ?new-row ?col))
    :effect (and (not (tile-at ?tile ?old-row ?col))
                 (not (is-blank ?new-row ?col))
                 (tile-at ?tile ?new-row ?col)
                 (is-blank ?old-row ?col)
                 (increase (total-cost) (WEIGHT ?tile))))

  (:action move-tile-right
    :parameters (?tile ?row ?old-col ?new-col)
    :precondition (and (is-tile ?tile)
    			(not (is-glued ?tile))
    			(is-col ?old-col)
    			(is-col ?new-col)
    			(is-row ?row)
    			(next-column ?old-col ?new-col)
                       (tile-at ?tile ?row ?old-col)
                       (is-blank ?row ?new-col))
    :effect (and (not (tile-at ?tile ?row ?old-col))
                 (not (is-blank ?row ?new-col))
                 (tile-at ?tile ?row ?new-col)
                 (is-blank ?row ?old-col)
                 (increase (total-cost) (WEIGHT ?tile))))

  (:action move-tile-left
    :parameters (?tile ?row ?old-col ?new-col)
    :precondition (and (is-tile ?tile)
    			(not (is-glued ?tile))
    			(is-col ?old-col)
    			(is-col ?new-col)
    			(is-row ?row)
    			(next-column ?new-col ?old-col)
                       (tile-at ?tile ?row ?old-col)
                       (is-blank ?row ?new-col))
    :effect (and (not (tile-at ?tile ?row ?old-col))
                 (not (is-blank ?row ?new-col))
                 (tile-at ?tile ?row ?new-col)
                 (is-blank ?row ?old-col)
                 (increase (total-cost) (WEIGHT ?tile))))

)