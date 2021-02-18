library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL; 

entity VGA_sim is
--  Port ( );
end VGA_sim;

architecture Behavioral of VGA_sim is

constant CLK_PERIOD : TIME := 10 ns;

signal Clk   : std_logic := '0';
signal Rst   : std_logic := '0';
signal R     : std_logic_vector(3 downto 0) := "0000";
signal G     : std_logic_vector(3 downto 0) := "0000";
signal B     : std_logic_vector(3 downto 0) := "0000";
signal HSync : std_logic;
signal VSync : std_logic;
signal Red   : std_logic_vector(3 downto 0);
signal Green : std_logic_vector(3 downto 0);
signal Blue  : std_logic_vector(3 downto 0);

begin

VGA: entity work.Top port map(
    Clk   => Clk,
    Rst   => Rst,
    R     => R,
    G     => G,
    B     => B, 
    HSync => HSync,
    VSync => VSync,
    Red   => Red,
    Green => Green,
    Blue  => Blue);

gen_clk: process
begin
    Clk <= '0';
    wait for (CLK_PERIOD/2);
    Clk <= '1';
    wait for (CLK_PERIOD/2);
end process gen_clk; 

gen_vec_test: process
    variable RedTest   : std_logic_vector(3 downto 0);
    variable GreenTest : std_logic_vector(3 downto 0);
    variable BlueTest  : std_logic_vector(3 downto 0);
    
    begin
        RedTest   := "0010";
        GreenTest := "0100";
        BlueTest  := "0001";
        
        for i in 0 to 3 loop 
            R <= RedTest;
            G <= GreenTest;
            B <= BlueTest;
            
            wait for 50 ns;
            
            RedTest   := RedTest + 1;
            GreenTest := GreenTest + 1;
            BlueTest  := BlueTest + 1;
        end loop;
end process;
end Behavioral;
