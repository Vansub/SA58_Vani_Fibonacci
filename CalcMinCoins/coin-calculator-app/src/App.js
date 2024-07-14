import axios from 'axios';
import React, { useState } from 'react';

const defaultDenominations = '1000, 100, 50, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.01';

function CoinCalculator() {
  const [amount, setAmount] = useState('');
  const [inputDenominations, setInputDenominations] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setResult(null);

    // Validate amount range
    if (parseFloat(amount) < 0 || parseFloat(amount) > 10000) {
      setError('Amount must be between 0 and 10000');
      return;
    }

    // Validate denominations
    const validDenominations = defaultDenominations.split(',').map(d => parseFloat(d.trim()));
    const inputDenoms = inputDenominations.split(',').map(d => parseFloat(d.trim()));

    if (inputDenoms.some(denom => !validDenominations.includes(denom))) {
      setError('Please input denominations from the given set.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/coins/calculate', {
        amount: parseFloat(amount),
        denominations: inputDenoms
      });
      
      setResult(response.data);
    } catch (err) {
      setError('An error occurred. Please try again.');
    }
  };

  const handleAmountChange = (e) => {
    const value = e.target.value;
    if (/^\d*\.?\d*$/.test(value)) {
      setAmount(value);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px' }}>
          <label htmlFor="amountInput">Enter amount:</label>
          <input 
            id="amountInput"
            type="text"
            inputMode="decimal"
            value={amount}
            onChange={handleAmountChange}
            placeholder="Enter amount"
            required
            style={{ WebkitAppearance: 'none', MozAppearance: 'textfield' }}
          />
        </div>
        <div style={{ marginBottom: '10px' }}>
          <p>Available denominations: {defaultDenominations}</p>
          <input
            type="text"
            value={inputDenominations}
            onChange={(e) => setInputDenominations(e.target.value)}
            placeholder="Enter denominations (comma-separated)"
            required
          />
        </div>
        <button type="submit">Calculate</button>
      </form>
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      {result && (
        <div>
          <h3>Result:</h3>
          <p>{result.join(', ')}</p>
        </div>
      )}
    </div>
  );
}

export default CoinCalculator;