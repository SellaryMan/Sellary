import React from 'react';
const Main = ({ children }: {
    children: React.ReactNode;
}) => {
    return(
        <>
            <div className='bg-gray-300'>
            {children}
            </div>
        </>
    )
}
export default Main;