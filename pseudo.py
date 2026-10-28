DAYS = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"]
input = new Scanner

func readSymbol()
  symbol = ""
  while symbol is empty:
    print("Enter the ticket symbol: ")
    symbol = input().trim().toUpperCase()
    if symbol is empty:
      print("Symbol cannot be empty")
  return symbol

func readPrices()
  prices = new double[5]
  for i from 0 to 4:
    valid = false
    white not valid:
      print("Enter the closing price for ${DAYS[i]}: ")
      entry = input().trim()
      if entry is not a number:
        print("Please enter a number.")
      else if entry <= 0:
        print("Price must be greater than 0")
      else:
        prices[i] = entry
        valid = true
  return prices

func calcAverage(prices)
  sum = 0
  for price in prices:
    sum = sum + price
  return sum / prices.length

func calcHigh(prices)
  high = 0
  for price in prices:
    if price > high:
      high = price
  return high

func calcLow(prices)
  low = prices[0]
  for price in prices:
    if price < low:
      low = price
  return low

func calcMove(prices)
  moveIndex = -1
  biggest = 0
  for i from 1 to 4:
    size = abs(prices[i] - prices[i - 1])

    if size > biggest:
      biggest = size
      moveIndex = i
  return moveIndex

MAIN FUNCTION:
  counter = 0
  keepGoing = true

  while keepGoing:
    symbol = readSymbol()
    prices = readPrices()

    average = calcAverage(prices)
    high = calcHigh(prices)
    low = calcLow(prices)
    weeklyChange = prices[4] - prices[0]
    weeklyPercent = weeklyChange / prices[0] * 100
    move = calcMove(prices)
    
    trend = "FLAT"

    if weeklyChange > 0:
      trend = "UP"
    elif weeklyChange < 0:
      trend = "DOWN"

    print("=== Weekly Summary for ${symbol} ===")
    print("Average: ${average}")
    print("High: ${high}")
    print("Low: ${low}")
    print("Weekly Change: ${weeklyChange}")
    print("Weekly Percent: ${weeklyPercent}")

    if move == -1:
      print("Move: No change")
    else:
      print("Move: ${move}")
    print("Trend: ${trend}")
    print("===================================")

    counter = counter + 1

    print "Analyze another stock? (y/n)"
    response = input().trim().toLowerCase()
    keepGoing = response == "y"
  print "Thank you for using the stock analyzer!"