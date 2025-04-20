import React from 'react';
const Main = ({ children }: {
    children: React.ReactNode;
}) => {
    return(
        <>
            <main className='bg-gray-300 m-1'>
            {children}
            </main>
        </>
    )
}
export default Main;