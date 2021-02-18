library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

entity Clk_Div is
    Port ( Clk     : in std_logic;
           Rst     : in std_logic;
           Clk25Hz : out std_logic);
end Clk_Div;

architecture Behavioral of Clk_Div is

signal count : integer   := 0;
signal tmp   : std_logic := '0';

begin

process(Clk, Rst)
begin
    if Rst = '1' then 
       count <= 0;
       tmp <= '0';
    elsif rising_edge(Clk) then 
       count <= count + 1;
       if count >= 1 then
          tmp <= not tmp;
          count <= 0; 
       end if;
     end if;
        
     Clk25Hz <= tmp;
end process;

end Behavioral;
