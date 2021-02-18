(define (problem twenty-four-puzzle)
  (:domain uncertainty)
  (:objects
     tile1 tile2 tile3 tile4 tile5 
     tile6 tile7 tile8 tile9 tile10 
     tile11 tile12 tile13 tile14 tile15 
     tile16 tile17 tile18 tile19 tile20 
     tile21 tile22 tile23 tile24
     
     row1 row2 row3 row4 row5
     col1 col2 col3 col4 col5)
  (:init
    (is-tile tile1)
    (is-tile tile2)
    (is-tile tile3)
    (is-tile tile4)
    (is-tile tile5)
    (is-tile tile6)
    (is-tile tile7)
    (is-tile tile8)
    (is-tile tile9)
    (is-tile tile10)
    (is-tile tile11)
    (is-tile tile12)
    (is-tile tile13)
    (is-tile tile14)
    (is-tile tile15)
    (is-tile tile16)
    (is-tile tile17)
    (is-tile tile18)
    (is-tile tile19)
    (is-tile tile20)
    (is-tile tile21)
    (is-tile tile22)
    (is-tile tile23)
    (is-tile tile24)
    
    (is-row row1)	
    (is-row row2)	
    (is-row row3)	
    (is-row row4)	
    (is-row row5)	
    
    
    (is-col col1)
    (is-col col2)
    (is-col col3)
    (is-col col4)
    (is-col col5)
    
    (next-row row1 row2)          
    (next-row row2 row3)      	   
    (next-row row3 row4)		  
    (next-row row4 row5)		  
    
    (next-column col1 col2)
    (next-column col2 col3)
    (next-column col3 col4)
    (next-column col4 col5)
    
    (unknown (is-glued tile1))
    (unknown (is-glued tile11))

    (or (is-glued tile1) (is-glued tile11))
    
    (tile-at tile24 row1 col1)	  
    (tile-at tile23 row1 col2)
    (tile-at tile22 row1 col3)	  
    (tile-at tile21 row1 col4)
    (tile-at tile20 row1 col5)	  
    
    (tile-at tile19 row2 col1)
    (tile-at tile18 row2 col2)	  
    (tile-at tile17 row2 col3)
    (tile-at tile16 row2 col4)	  
    (tile-at tile15 row2 col5)
   	
   	(tile-at tile14 row3 col1)	  
   	(tile-at tile13 row3 col2)
    (tile-at tile12 row3 col3)	  
    (tile-at tile11 row3 col4)
    (tile-at tile10 row3 col5)	
      
    (tile-at tile9 row4 col1)
    (tile-at tile8 row4 col2)	  
    (tile-at tile7 row4 col3)
    (tile-at tile6 row4 col4)	  
    (tile-at tile5 row4 col5)
    
    (tile-at tile4 row5 col1)	  
    (tile-at tile3 row5 col2)
    (tile-at tile2 row5 col3)	  
    (tile-at tile1 row5 col4)
    (is-blank row5 col5)
  )
    
  (:goal
   (and
    (tile-at tile1 row1 col1)	  (tile-at tile2 row1 col2)
    (tile-at tile3 row1 col3)	  (tile-at tile4 row1 col4)
    (tile-at tile5 row1 col5)	  (tile-at tile6 row2 col1)
    (tile-at tile7 row2 col2)	  (tile-at tile8 row2 col3)
    (tile-at tile9 row2 col4)	  (tile-at tile10 row2 col5)
   	(tile-at tile11 row3 col1)	  (tile-at tile12 row3 col2)
    (tile-at tile13 row3 col3)	  (tile-at tile14 row3 col4)
    (tile-at tile15 row3 col5)	  (tile-at tile16 row4 col1)
    (tile-at tile17 row4 col2)	  (tile-at tile18 row4 col3)
    (tile-at tile19 row4 col4)	  (tile-at tile20 row4 col5)
    (tile-at tile21 row5 col1)	  (tile-at tile22 row5 col2)
    (tile-at tile23 row5 col3)	  (tile-at tile24 row5 col4)))    
)
